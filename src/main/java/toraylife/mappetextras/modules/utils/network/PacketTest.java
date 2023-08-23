package toraylife.mappetextras.modules.utils.network;

import io.netty.buffer.ByteBuf;
import mchorse.mclib.network.ClientMessageHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketTest implements IMessage {

    public PacketTest() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class ClientHandlerTest extends ClientMessageHandler<PacketTest> {
        @Override
        public void run(EntityPlayerSP player, PacketTest message) {
            player.sendChatMessage("You fool!");
        }
    }
}

