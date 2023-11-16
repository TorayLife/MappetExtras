package toraylife.mappetextras.modules.client.network;

import io.netty.buffer.ByteBuf;
import mchorse.mclib.network.ServerMessageHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import toraylife.mappetextras.CommonProxy;

public class PacketGuiOpenEvent implements IMessage {
    String gui;

    public PacketGuiOpenEvent() {
    }

    public PacketGuiOpenEvent(String gui) {
        this.gui = gui;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.gui = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.gui);
    }

    public static class ServerHandler extends ServerMessageHandler<PacketGuiOpenEvent> {
        @Override
        public void run(EntityPlayerMP entityPlayerMP, PacketGuiOpenEvent packet) {
            String gui = packet.gui;

            CommonProxy.eventTriggerHandler.onPlayerOpenGui(gui, entityPlayerMP);
        }
    }
}