package toraylife.mappetextras.modules.client.network;

import io.netty.buffer.ByteBuf;
import mchorse.mclib.network.ClientMessageHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketRenderWithEntity implements IMessage {
    int id;
    public PacketRenderWithEntity() {
    }

    public PacketRenderWithEntity(int id) {
        this.id = id;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.id = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.id);
    }

    public static class ClientHandler extends ClientMessageHandler<PacketRenderWithEntity> {
        @Override
        @SideOnly(Side.CLIENT)
        public void run(EntityPlayerSP player, PacketRenderWithEntity message) {
            Entity entity = Minecraft.getMinecraft().player.world.getEntityByID(message.id);

            Minecraft.getMinecraft().setRenderViewEntity(entity);
        }
    }
}
