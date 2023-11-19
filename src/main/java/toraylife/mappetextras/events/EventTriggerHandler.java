package toraylife.mappetextras.events;

import mchorse.mappet.CommonProxy;
import mchorse.mappet.Mappet;
import mchorse.mappet.api.triggers.Trigger;
import mchorse.mappet.api.utils.DataContext;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import toraylife.mappetextras.modules.main.triggers.TriggerAccessor;

public class EventTriggerHandler {

    @SubscribeEvent
    public void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {
        if (Mappet.settings == null) {
            return;
        }
        Trigger trigger = ((TriggerAccessor) Mappet.settings).getPlayerTick();

        EntityPlayer player = event.player;

        if (shouldCancelTrigger(trigger) || player.world.isRemote) {
            return;
        }

        trigger.trigger(new DataContext(player));
    }

    @SubscribeEvent
    public void onPlayerWalkingEvent(TickEvent.PlayerTickEvent event) {
        if (Mappet.settings == null) {
            return;
        }
        Trigger trigger = ((TriggerAccessor) Mappet.settings).getPlayerWalking();

        EntityPlayer player = event.player;

        if (shouldCancelTrigger(trigger) || player.world.isRemote) {
            return;
        }

        if (player.prevDistanceWalkedModified - player.distanceWalkedModified == 0) {
            return;
        }

        DataContext context = new DataContext(player);
        context.set("onGround", player.onGround ? 1 : 0);

        trigger.trigger(context);
    }

    @SubscribeEvent
    public void onLivingJumpEvent(LivingEvent.LivingJumpEvent event) {
        if (Mappet.settings == null) {
            return;
        }
        Trigger trigger = ((TriggerAccessor) Mappet.settings).getEntityJumping();

        EntityLivingBase entity = event.getEntityLiving();

        if (shouldCancelTrigger(trigger) || entity.world.isRemote) {
            return;
        }

        CommonProxy.eventHandler.trigger(event, trigger, new DataContext(entity));
    }

    @SubscribeEvent
    public void onLivingFallEvent(LivingFallEvent event) {
        if (Mappet.settings == null) {
            return;
        }
        Trigger trigger = ((TriggerAccessor) Mappet.settings).getEntityFalling();

        EntityLivingBase entity = event.getEntityLiving();

        if (shouldCancelTrigger(trigger) || entity.world.isRemote) {
            return;
        }

        DataContext context = new DataContext(entity);
        context.set("distance", event.getDistance());
        context.set("damageMultiplier", event.getDamageMultiplier());

        CommonProxy.eventHandler.trigger(event, trigger, context);
    }


    public void onPlayerOpenGui(String gui, EntityPlayer player) {
        if (Mappet.settings == null) {
            return;
        }
        Trigger trigger = ((TriggerAccessor) Mappet.settings).getPlayerOpenGui();

        if (shouldCancelTrigger(trigger) || player.world.isRemote) {
            return;
        }

        DataContext context = new DataContext(player);

        context.set("gui", gui);

        trigger.trigger(context);
    }

    public boolean shouldCancelTrigger(Trigger trigger) {
        return trigger == null || trigger.isEmpty();
    }
}
