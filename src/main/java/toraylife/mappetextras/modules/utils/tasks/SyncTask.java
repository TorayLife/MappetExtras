package toraylife.mappetextras.modules.utils.tasks;

import mchorse.mappet.CommonProxy;
import mchorse.mappet.utils.RunnableExecutionFork;

import java.util.function.Function;

public class SyncTask<TConsume, TResult> extends RegularTask<TConsume, TResult> {

	public SyncTask(Task<Void, ?> initTask,
	                TaskDelayTime timeoutDelay,
	                TaskExecutable<TConsume, TResult> executable) {
		super(Type.SYNC, initTask, timeoutDelay, executable);
	}


	@Override
	public void schedule(TaskContext<TConsume> taskContext) {
		Runnable scriptRunnable = () -> {
			Object objectResult = this.executable.execute(taskContext);
			TaskResult<TResult> taskResult = TaskResult.from(objectResult);

			if (taskResult instanceof DelegateTaskResult<?>) {
				DelegateTaskResult<TResult> delegateResult = (DelegateTaskResult<TResult>) taskResult;
				Task<?, TResult> delegateTask = delegateResult.getDelegateTask();

				delegateTask.nextTask = this.nextTask;

				delegateTask.initTask.schedule(
						new TaskContext<>(delegateTask.initTask, taskContext.scriptContext, null)
				);
			}
			else if (taskResult instanceof ValueTaskResult<?> && this.nextTask != null) {
				ValueTaskResult<TResult> valueTaskResult = (ValueTaskResult<TResult>) taskResult;
				this.nextTask.schedule(new TaskContext<>(this.nextTask, taskContext.scriptContext, valueTaskResult.getValue()));
			}
		};

		CommonProxy.eventHandler.addExecutable(
				new RunnableExecutionFork(this.timeoutDelay.toTicks().getDelay(), scriptRunnable));
	}

}
