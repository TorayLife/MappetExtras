package toraylife.mappetextras.events;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import toraylife.mappetextras.modules.utils.network.PacketTest;
import toraylife.mappetextras.network.Dispatcher;

public class EventHandler {

    @SubscribeEvent
    public void onPlayerJoinEvent(PlayerEvent.PlayerLoggedInEvent event) {
        Dispatcher.sendTo(new PacketTest(), (EntityPlayerMP)event.player);
    }
}
