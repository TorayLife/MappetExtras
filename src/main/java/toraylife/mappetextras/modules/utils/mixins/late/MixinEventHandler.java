package toraylife.mappetextras.modules.utils.mixins.late;

import mchorse.mappet.EventHandler;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toraylife.mappetextras.modules.utils.tasks.SyncTaskScheduleDefinition;
import toraylife.mappetextras.modules.utils.tasks.TaskLoop;

import java.util.concurrent.ConcurrentLinkedQueue;

@Mixin(value = EventHandler.class, remap = false)
public class MixinEventHandler {

	@Inject(method = "onServerTick", at = @At(value = "INVOKE", target = "Ljava/util/Set;clear()V"))
	public void onServerTick(TickEvent.ServerTickEvent event, CallbackInfo ci) {
		ConcurrentLinkedQueue<SyncTaskScheduleDefinition> asyncTaskQueue = TaskLoop.getInstance().getSyncTaskScheduleDefinitionQueue();

		SyncTaskScheduleDefinition queuedContextualizedTaskDefinition = asyncTaskQueue.poll();

		if (queuedContextualizedTaskDefinition != null) {
			queuedContextualizedTaskDefinition.schedule();
		}
	}

}
