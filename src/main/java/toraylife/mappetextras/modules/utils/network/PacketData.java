package toraylife.mappetextras.modules.utils.network;

import io.netty.buffer.ByteBuf;
import mchorse.mappet.api.utils.AbstractData;
import mchorse.mappet.client.gui.GuiMappetDashboard;
import mchorse.mclib.network.ClientMessageHandler;
import mchorse.mclib.network.ServerMessageHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import toraylife.mappetextras.modules.utils.DataType;
import toraylife.mappetextras.modules.utils.client.gui.panels.BaseDataPanel;
import toraylife.mappetextras.network.Dispatcher;

import java.util.HashSet;
import java.util.Set;

public class PacketData implements IMessage {

    public DataInteractionType interactionType;

    public String id;

    public AbstractData data;

    public DataType type;

    public Set<String> keys;

    public PacketData() {
    }

    public PacketData(DataInteractionType interactionType, DataType type, String id, AbstractData data) {
        this.interactionType = interactionType;
        this.type = type;
        this.id = id;
        if (interactionType == DataInteractionType.SAVE) {
            this.data = data == null ? type.manager.create(id) : data;
        }
    }

    public PacketData(DataInteractionType interactionType, DataType type, Set<String> keys) {
        this.interactionType = interactionType;
        this.type = type;
        this.id = "";
        this.keys = keys;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.interactionType = DataInteractionType.values()[buf.readInt()];
        this.type = DataType.values()[buf.readInt()];
        this.id = ByteBufUtils.readUTF8String(buf);
        if (this.interactionType == DataInteractionType.SAVE) {
            this.data = this.type.manager.create(this.id);
            this.data.deserializeNBT(ByteBufUtils.readTag(buf));
        } else if (this.interactionType == DataInteractionType.FETCH) {
            int size = buf.readInt();
            this.keys = new HashSet<>();
            while (size > 0) {
                this.keys.add(ByteBufUtils.readUTF8String(buf));
                size--;
            }
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(interactionType.ordinal());
        buf.writeInt(type.ordinal());
        ByteBufUtils.writeUTF8String(buf, this.id);
        if (this.interactionType == DataInteractionType.SAVE) {
            ByteBufUtils.writeTag(buf, this.data.serializeNBT());
        } else if (this.interactionType == DataInteractionType.FETCH) {
            buf.writeInt(this.keys.size());
            this.keys.forEach(s -> ByteBufUtils.writeUTF8String(buf, s));
        }
    }

    public static class ServerHandler extends ServerMessageHandler<PacketData> {
        @Override
        public void run(EntityPlayerMP entityPlayerMP, PacketData packetData) {
            switch (packetData.interactionType) {
                case SAVE:
                    packetData.type.manager.save(packetData.data.getId(), packetData.data.serializeNBT());
                    break;
                case LOAD:
                    Dispatcher.sendTo(new PacketData(DataInteractionType.LOAD, packetData.type, packetData.data.getId(), packetData.type.manager.load(packetData.id)), entityPlayerMP);
                    break;
                case FETCH:
                    Set<String> keys = (Set<String>) packetData.type.manager.getKeys();
                    Dispatcher.sendTo(new PacketData(DataInteractionType.FETCH, packetData.type, keys), entityPlayerMP);
                    break;
            }
        }
    }

    public static class ClientHandler extends ClientMessageHandler<PacketData> {

        @Override
        public void run(EntityPlayerSP entityPlayerSP, PacketData packetData) {
            switch (packetData.interactionType) {
                case LOAD:

                    break;
                case FETCH:
                    GuiMappetDashboard dashboard = GuiMappetDashboard.get(Minecraft.getMinecraft());
                    BaseDataPanel<? extends AbstractData> panel = packetData.type.getPanel(dashboard);
                    panel.list.list.clear();
                    panel.list.list.add(packetData.keys);
                    break;
            }
        }
    }
}

