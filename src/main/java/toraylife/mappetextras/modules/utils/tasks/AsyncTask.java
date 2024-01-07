package toraylife.mappetextras.modules.utils.tasks;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;


public class AsyncTask<TConsume, TResult> extends RegularTask<TConsume, TResult> {

	public AsyncTask(Task<Void, ?> initTask,
	                 TaskDelayTime timeoutDelay,
	                 Function<TaskContext<TConsume>, TResult> executable) {
		super(Type.ASYNC, initTask, timeoutDelay, executable);
	}


	@Override
	@SuppressWarnings("unchecked")
	public void schedule(TaskContext<TConsume> taskContext) {
		TaskLoop.getInstance().getExecutorService().schedule(() -> {
			TResult resultValue = this.executable.apply(taskContext);
			TaskResult taskResult = taskContext.getCurrentResult();

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
				scheduleNextTask(
						this.nextTask,
						new TaskContext<>(this.nextTask, taskContext.getScriptContext(), resultValue)
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
			SyncTaskScheduleDefinition scheduleDef = new SyncTaskScheduleDefinition(nextTask, nextTaskContext);

			TaskLoop.getInstance().getSyncTaskScheduleDefinitionQueue().offer(scheduleDef);
		}
	}

}
