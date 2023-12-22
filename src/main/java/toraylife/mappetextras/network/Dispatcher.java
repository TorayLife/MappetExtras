package toraylife.mappetextras.network;

import java.util.Iterator;

import mchorse.mclib.network.AbstractDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import toraylife.mappetextras.MappetExtras;
import toraylife.mappetextras.modules.client.network.*;
import toraylife.mappetextras.modules.utils.network.PacketTest;

public class Dispatcher {
    public static final AbstractDispatcher DISPATCHER = new AbstractDispatcher(MappetExtras.MOD_ID) {
        public void register() {
            this.register(PacketTest.class, PacketTest.ClientHandlerTest.class, Side.CLIENT);
            this.register(PacketClientData.class, PacketClientData.ClientHandler.class, Side.CLIENT);
            this.register(PacketClientData.class, PacketClientData.ServerHandler.class, Side.SERVER);
            this.register(PacketEvent.class, PacketEvent.ServerHandler.class, Side.SERVER);
            this.register(PacketCapability.class, PacketCapability.ClientHandler.class, Side.CLIENT);
            this.register(PacketNPCStateChange.class, PacketNPCStateChange.ClientHandler.class, Side.CLIENT);
        }
    };

    public static void sendToTracked(Entity entity, IMessage message) {
        EntityTracker tracker = ((WorldServer)entity.world).getEntityTracker();
        Iterator var3 = tracker.getTrackingPlayers(entity).iterator();

        while(var3.hasNext()) {
            EntityPlayer player = (EntityPlayer)var3.next();
            sendTo(message, (EntityPlayerMP)player);
        }

    }

    public static void sendTo(IMessage message, EntityPlayerMP player) {
        DISPATCHER.sendTo(message, player);
    }

    public static void sendToServer(IMessage message) {
        DISPATCHER.sendToServer(message);
    }

    public static void register() {
        DISPATCHER.register();
    }
}
