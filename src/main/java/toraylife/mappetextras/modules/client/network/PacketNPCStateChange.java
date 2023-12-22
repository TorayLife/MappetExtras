package toraylife.mappetextras.modules.client.network;

import io.netty.buffer.ByteBuf;
import mchorse.mappet.api.npcs.NpcState;
import mchorse.mappet.entities.EntityNpc;
import mchorse.mappet.utils.NpcStateUtils;
import mchorse.mclib.network.ClientMessageHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketNPCStateChange implements IMessage {
    public int id;
    public NpcState state;

    public PacketNPCStateChange() {
    }

    public PacketNPCStateChange(int id, NpcState state) {
        this.id = id;
        this.state = state;
    }

    public void fromBytes(ByteBuf buf) {
        this.id = buf.readInt();
        this.state = NpcStateUtils.stateFromBuf(buf);
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.id);
        NpcStateUtils.stateToBuf(buf, this.state);
    }

    public static class ClientHandler extends ClientMessageHandler<PacketNPCStateChange> {
        @Override
        @SideOnly(Side.CLIENT)
        public void run(EntityPlayerSP player, PacketNPCStateChange message) {
            Entity entity = player.world.getEntityByID(message.id);
            if (entity instanceof EntityNpc) {
                ((EntityNpc)entity).setState(message.state, false);
            }
        }
    }
}
