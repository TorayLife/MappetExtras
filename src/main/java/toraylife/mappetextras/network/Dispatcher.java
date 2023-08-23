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

public class Dispatcher {
    public static final AbstractDispatcher DISPATCHER = new AbstractDispatcher(MappetExtras.MOD_ID) {
        public void register() {

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
