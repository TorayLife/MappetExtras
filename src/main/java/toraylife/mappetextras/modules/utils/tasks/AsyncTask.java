package toraylife.mappetextras.modules.utils.tasks;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;


public class AsyncTask<TConsume, TResult> extends Task<TConsume, TResult> {

	public AsyncTask(Function<TaskContext<TConsume>, TResult> executable, TaskDelayTime delayTime) {
		super(executable, delayTime);
	}

	public AsyncTask(Function<TaskContext<TConsume>, TResult> executable) {
		super(executable);
	}

	public AsyncTask(Task<Void, ?> initTask,
	                 Function<TaskContext<TConsume>, TResult> executable,
	                 TaskDelayTime delayTime) {
		super(initTask, executable, delayTime);
	}

	public AsyncTask(Task<Void, ?> initTask, Function<TaskContext<TConsume>, TResult> executable) {
		super(initTask, executable);
	}


	@Override
	@SuppressWarnings("unchecked")
	public void schedule(TaskContext<TConsume> taskContext) {
		TaskLoop.getInstance().getExecutorService().schedule(() -> {
			TResult resultValue = this.getExecutable().apply(taskContext);
			TaskResult taskResult = taskContext.getResult();

			Task<TResult, ?> nextTask = this.getNextTask();

			if (taskResult instanceof DelegateTaskResult<?>) {
				DelegateTaskResult<TResult> delegateResult = (DelegateTaskResult<TResult>) taskResult;
				Task<?, TResult> delegateTask = delegateResult.getDelegateTask();

				Task<Void, ?> actualNextTask = delegateTask.getInitTask();
				delegateTask.setInitTask(this.getInitTask());
				delegateTask.setNextTask(nextTask);

				scheduleNextTask(
						actualNextTask,
						new TaskContext<>(actualNextTask, taskContext.getScriptContext(), null)
				);
			}
			else if (!(taskResult instanceof UnresolvedTaskResult) && nextTask != null) {
				scheduleNextTask(
						nextTask,
						new TaskContext<>(nextTask, taskContext.getScriptContext(), resultValue)
				);
			}
		}, this.getDelayTime().toMillis().getDelay(), TimeUnit.MILLISECONDS);
	}


	@SuppressWarnings({"rawtypes", "unchecked"})
	private void scheduleNextTask(Task nextTask, TaskContext nextTaskContext) {
		if (nextTask instanceof AsyncTask) {
			nextTask.schedule(nextTaskContext);
		}
		else {
			SyncTaskScheduleDefinition resultContext =
					new SyncTaskScheduleDefinition((SyncTask<?, ?>) nextTask, nextTaskContext);

			TaskLoop.getInstance().getSyncTaskScheduleDefinitionQueue().offer(resultContext);
		}
	}

}
