package toraylife.mappetextras.modules.utils.network;

import io.netty.buffer.ByteBuf;
import mchorse.mappet.CommonProxy;
import mchorse.mappet.api.scripts.ScriptExecutionFork;
import mchorse.mclib.network.ServerMessageHandler;
import mchorse.mclib.utils.OpHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.modules.utils.dimensions.Dimension;

public class PacketRegistrationDimension implements IMessage {

    public String id;
    public boolean unregister = false;

    public PacketRegistrationDimension() {
    }
    public PacketRegistrationDimension(Dimension data) {
        this.id = data.getId();
    }

    public PacketRegistrationDimension(String id) {
        this.id = id;
    }

    public PacketRegistrationDimension(String id, boolean unregister) {
        this.id = id;
        this.unregister = unregister;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.id = ByteBufUtils.readUTF8String(buf);
        this.unregister = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.id);
        buf.writeBoolean(this.unregister);
    }

    public static class ServerHandler extends ServerMessageHandler<PacketRegistrationDimension> {

        @Override
        public void run(EntityPlayerMP player, PacketRegistrationDimension message) {
            //TODO rewrite with Task API
            CommonProxy.eventHandler.addExecutable(new ScriptExecutionFork(null, a-> {
                Dimension dimension = MappetExtras.customDimensionManager.load(message.id);
                if (!OpHelper.isPlayerOp(player) || dimension == null) {
                    return;
                }
                try {
                    if (message.unregister) {
                        dimension.unregister();
                    } else {
                        dimension.register();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    player.sendStatusMessage(new TextComponentString("\u00A7cError: " + e.getMessage()), false);
                }
            }, 5));
        }
    }
}
