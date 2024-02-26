package toraylife.mappetextras.modules.utils.tasks;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;


public class AsyncTask<TConsume, TResult> extends RegularTask<TConsume, TResult> {

	public AsyncTask(Task<Void, ?> initTask,
	                 TaskDelayTime timeoutDelay,
	                 Function<TaskContext<TConsume>, TaskResult<TResult>> executable) {
		super(Type.ASYNC, initTask, timeoutDelay, executable);
	}


	@Override
	public void schedule(TaskContext<TConsume> taskContext) {
		TaskLoop.getInstance().getExecutorService().schedule(() -> {
			Object objectResult = this.executable.apply(taskContext);
			TaskResult<TResult> taskResult = TaskResult.from(objectResult);

			if (taskResult instanceof DelegateTaskResult<?>) {
				DelegateTaskResult<TResult> delegateResult = (DelegateTaskResult<TResult>) taskResult;
				Task<?, TResult> delegateTask = delegateResult.getDelegateTask();

				delegateTask.nextTask = this.nextTask;

				scheduleNextTask(
						delegateTask.initTask,
						new TaskContext<>(delegateTask.initTask, taskContext.getScriptContext(), null)
				);
			}
			else if (!(taskResult instanceof UnresolvedTaskResult) && this.nextTask != null) {
				ValueTaskResult<TResult> valueTaskResult = (ValueTaskResult<TResult>) taskResult;
				scheduleNextTask(
						this.nextTask,
						new TaskContext<>(this.nextTask, taskContext.getScriptContext(), valueTaskResult.getValue())
				);
			}
		}, this.timeoutDelay.toMillis().getDelay(), TimeUnit.MILLISECONDS);
	}


	@SuppressWarnings({"rawtypes", "unchecked"})
	private void scheduleNextTask(Task nextTask, TaskContext nextTaskContext) {
		if (nextTask.type == Type.ASYNC) {
			nextTask.schedule(nextTaskContext);
		}
		else {
			SyncTaskScheduleDef scheduleDef = new SyncTaskScheduleDef(nextTask, nextTaskContext);

			TaskLoop.getInstance().getSyncTaskScheduleQueue().offer(scheduleDef);
		}
	}

}
