package toraylife.mappetextras.modules.utils.network;

import io.netty.buffer.ByteBuf;
import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import mchorse.mclib.network.ServerMessageHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketChangeDimension implements IMessage {
    int dimensionId;

    public PacketChangeDimension(){

    }
    public PacketChangeDimension(int dimensionId) {
        this.dimensionId = dimensionId;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.dimensionId = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.dimensionId);
    }

    public static class ServerHandler extends ServerMessageHandler<PacketChangeDimension> {

        @Override
        public void run(EntityPlayerMP player, PacketChangeDimension message) {
            ScriptPlayer.create(player).setDimension(message.dimensionId);
        }
    }
}

