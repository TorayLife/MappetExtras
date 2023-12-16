package toraylife.mappetextras.modules.client.network;

import io.netty.buffer.ByteBuf;
import mchorse.mclib.network.ClientMessageHandler;
import mchorse.mclib.network.ServerMessageHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import toraylife.mappetextras.modules.client.AccessType;
import toraylife.mappetextras.modules.client.ClientData;
import toraylife.mappetextras.modules.client.providers.*;
import toraylife.mappetextras.network.Dispatcher;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class PacketClientData implements IMessage {
    public static final Map<UUID, Consumer<Object>> сallBack = new HashMap<>();
    ClientData type;
    AccessType access;
    NBTTagCompound nbtTagCompound;
    NBTTagCompound nbtData;
    String uniqueId;

    public PacketClientData() {
    }

    public PacketClientData(ClientData type, AccessType access, UUID uniqueId) {
        this.type = type;
        this.access = access;
        this.nbtTagCompound = new NBTTagCompound();
        this.nbtData = new NBTTagCompound();
        this.uniqueId = uniqueId.toString();
    }

    public PacketClientData(NBTTagCompound nbtData, ClientData type, AccessType access, UUID uniqueId) {
        this.type = type;
        this.access = access;
        this.nbtTagCompound = new NBTTagCompound();
        this.nbtData = nbtData;
        this.uniqueId = uniqueId.toString();
    }

    public PacketClientData(ClientData type, AccessType access, NBTTagCompound nbtTagCompound) {
        this.type = type;
        this.access = access;
        this.nbtTagCompound = nbtTagCompound;
        this.nbtData = new NBTTagCompound();
        this.uniqueId = "";
    }

    public PacketClientData(ClientData type, AccessType access, NBTTagCompound nbtTagCompound, UUID uniqueId) {
        this.type = type;
        this.access = access;
        this.nbtTagCompound = nbtTagCompound;
        this.nbtData = new NBTTagCompound();
        this.uniqueId = uniqueId.toString();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.type = ClientData.valueOf(ByteBufUtils.readUTF8String(buf)); // Enum type
        this.access = AccessType.valueOf(ByteBufUtils.readUTF8String(buf)); // Enum access
        this.nbtTagCompound = ByteBufUtils.readTag(buf); // nbtTagCompound
        this.nbtData = ByteBufUtils.readTag(buf); // nbtData
        this.uniqueId = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.type.toString());
        ByteBufUtils.writeUTF8String(buf, this.access.toString());
        ByteBufUtils.writeTag(buf, this.nbtTagCompound);
        ByteBufUtils.writeTag(buf, this.nbtData);
        ByteBufUtils.writeUTF8String(buf, this.uniqueId);
    }

    public static class ClientHandler extends ClientMessageHandler<PacketClientData> {
        @Override
        public void run(EntityPlayerSP player, PacketClientData message) {
            ClientData typeEnum = message.type;
            AccessType typeAccess = message.access;
            NBTTagCompound value = message.nbtTagCompound;
            NBTTagCompound nbtData = message.nbtData;
            String uniqueId = message.uniqueId;
            IClientDataProvider provider = createProvider(typeEnum);

            switch (typeAccess) {
                case GET:
                    NBTTagCompound data = provider.getData();

                    Dispatcher.sendToServer(new PacketClientData(typeEnum, typeAccess, data, UUID.fromString(uniqueId)));
                case SET:
                    try {
                        provider.setData(value);
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                case USE:
                    break;
                case GET_WITH_DATA:
                    NBTTagCompound dataWithResponse;
                    try {
                        dataWithResponse = provider.getData(nbtData);
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }

                    Dispatcher.sendToServer(new PacketClientData(typeEnum, typeAccess, dataWithResponse, UUID.fromString(uniqueId)));
            }
        }

        public static IClientDataProvider createProvider(ClientData typeEnum) {

            switch(typeEnum) {
                case PESPECTIVE:
                    return new PerspectiveProvider();
                case CLIPBOARD:
                    return new ClipboardProvider();
                case MOUSEPOSITION:
                    return new MousePositionProvider();
                case SETTING:
                    return new SettingProvider();
                case RESOLUTION:
                    return new ResolutionProvider();
                case WEB_LINK:
                    return new WebLinkProvider();
            }
            throw new IllegalArgumentException("Invalid typeEnum");
        }
    }

    public static class ServerHandler extends ServerMessageHandler<PacketClientData> {
        @Override
        public void run(EntityPlayerMP entityPlayerMP, PacketClientData packet) {
            NBTTagCompound value = packet.nbtTagCompound;
            UUID uniqueId = UUID.fromString(packet.uniqueId);

            сallBack.get(uniqueId).accept(packet.type.process(value));
            сallBack.remove(uniqueId);
        }
    }
}