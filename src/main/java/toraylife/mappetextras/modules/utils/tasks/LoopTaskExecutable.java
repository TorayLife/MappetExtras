package toraylife.mappetextras.modules.utils.tasks;

@FunctionalInterface
public interface LoopTaskExecutable<TConsume, TAccumulator> {

	TaskResult<TAccumulator> execute(LoopTaskContext<TConsume, TAccumulator> context);

}
