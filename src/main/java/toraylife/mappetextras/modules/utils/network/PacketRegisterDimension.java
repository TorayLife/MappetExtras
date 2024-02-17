package toraylife.mappetextras.modules.utils.network;

import io.netty.buffer.ByteBuf;
import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import mchorse.mclib.network.ServerMessageHandler;
import mchorse.mclib.utils.OpHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.modules.utils.dimensions.CustomDimensionManager;
import toraylife.mappetextras.modules.utils.dimensions.Dimension;

public class PacketRegisterDimension implements IMessage {

    public String id;

    public PacketRegisterDimension() {
    }
    public PacketRegisterDimension(Dimension data) {
        this.id = data.getId();
    }

    public PacketRegisterDimension(String id) {
        this.id = id;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.id = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.id);
    }

    public static class ServerHandler extends ServerMessageHandler<PacketRegisterDimension> {

        @Override
        public void run(EntityPlayerMP player, PacketRegisterDimension message) {
            Dimension dimensionToRegister = MappetExtras.customDimensionManager.load(message.id);
            if (OpHelper.isPlayerOp(player) && dimensionToRegister != null){
                try {
                    dimensionToRegister.register();
                }
                catch (Exception e) {
                    e.printStackTrace();
                    player.sendStatusMessage(new TextComponentString("\u00A7cError: " + e.getMessage()), false);
                }
            }
        }
    }
}
