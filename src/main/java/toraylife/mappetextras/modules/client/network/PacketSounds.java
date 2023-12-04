package toraylife.mappetextras.modules.client.network;

import io.netty.buffer.ByteBuf;
import mchorse.mclib.network.ClientMessageHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import toraylife.mappetextras.modules.client.AccessType;

public class PacketSounds implements IMessage {
    AccessType access;
    public PacketSounds() {
    }

    public PacketSounds(AccessType access) {
        this.access = access;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.access = AccessType.valueOf(ByteBufUtils.readUTF8String(buf)); // Enum access
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.access.toString());
    }

    public static class ClientHandler extends ClientMessageHandler<PacketSounds> {
        @Override
        @SideOnly(Side.CLIENT)
        public void run(EntityPlayerSP player, PacketSounds message) {
            AccessType typeAccess = message.access;
            switch (typeAccess){
                case UPDATE:
                    Minecraft.getMinecraft().getSoundHandler().update();
            }
        }
    }
}
