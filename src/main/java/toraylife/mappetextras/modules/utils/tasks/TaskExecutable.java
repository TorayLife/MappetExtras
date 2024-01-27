package toraylife.mappetextras.modules.utils.tasks;

@FunctionalInterface
public interface TaskExecutable<TConsume, TResult> {

	TaskResult<TResult> execute(TaskContext<TConsume> context);

}
