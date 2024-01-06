package toraylife.mappetextras.modules.utils.tasks;

import jdk.nashorn.internal.runtime.Undefined;
import org.apache.commons.lang3.NotImplementedException;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;


public class AsyncTask<TConsume, TResult> extends Task<TConsume, TResult> {

	public AsyncTask(Function<TaskContext<TConsume>, TaskResult<TResult>> executable,
	                 TaskDelayTime delayTime) {
		super(executable, delayTime);
	}

	public AsyncTask(Function<TaskContext<TConsume>, TaskResult<TResult>> executable) {
		super(executable);
	}

	public AsyncTask(Task<Void, ?> initTask,
	                 Function<TaskContext<TConsume>, TaskResult<TResult>> executable,
	                 TaskDelayTime delayTime) {
		super(initTask, executable, delayTime);
	}

	public AsyncTask(Task<Void, ?> initTask,
	                 Function<TaskContext<TConsume>, TaskResult<TResult>> executable) {
		super(initTask, executable);
	}


	@Override
	public void schedule(TaskContext<TConsume> taskContext) {
		TaskLoop.getInstance().getExecutorService().schedule(() -> {
			Object objectResult = this.getExecutable().apply(taskContext);
			if (objectResult instanceof Undefined) {
				objectResult = null;
			}
			TaskResult<TResult> taskResult = (TaskResult<TResult>) objectResult;

			if ((taskResult == null || taskResult instanceof ValueTaskResult<?>) && this.getNextTask() != null) {
				ValueTaskResult<TResult> valueResult = (ValueTaskResult<TResult>) taskResult;
				TResult resultValue = valueResult != null ? valueResult.getValue() : null;
				scheduleNextTask(
						this.getNextTask(),
						new TaskContext<>(this.getNextTask(), taskContext.getScriptContext(), resultValue)
				);
			}
			else if (taskResult instanceof DelegateTaskResult<?>) {
				DelegateTaskResult<TResult> delegateResult = (DelegateTaskResult<TResult>) taskResult;
				Task<?, TResult> delegateTask = delegateResult.getDelegateTask();

				Task<Void, ?> actualNextTask = delegateTask.getInitTask();
				delegateTask.setInitTask(this.getInitTask());
				delegateTask.setNextTask(this.getNextTask());

				scheduleNextTask(
						actualNextTask,
						new TaskContext<>(actualNextTask, taskContext.getScriptContext(), null)
				);
			}
			else {
//				throw new NotImplementedException("Unknown Task result type");
			}
		}, this.getDelayTime().toMillis().getDelay(), TimeUnit.MILLISECONDS);
	}


	@SuppressWarnings({"rawtypes", "unchecked"})
	private void scheduleNextTask(Task nextTask, TaskContext nextTaskContext) {
		if (nextTask instanceof AsyncTask) {
			nextTask.schedule(nextTaskContext);
		} else {
			SyncTaskScheduleDefinition resultContext = new SyncTaskScheduleDefinition(
					(SyncTask<?, ?>) nextTask,
					nextTaskContext
			);
			TaskLoop.getInstance().getSyncTaskScheduleDefinitionQueue().offer(resultContext);
		}
	}

}
