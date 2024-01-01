package toraylife.mappetextras.modules.utils.tasks;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;


public class AsyncTask<TSupply, TResult> extends Task<TSupply, TResult> {

	public AsyncTask(Task<Void, ?> initTask, Function<TaskContext<TSupply>, TResult> executable) {
		super(initTask, executable);
	}

	public AsyncTask(Task<Void, ?> initTask, Function<TaskContext<TSupply>, TResult> executable, TaskDelayTime delayTime) {
		super(initTask, executable, delayTime);
	}

	public AsyncTask(Function<TaskContext<TSupply>, TResult> executable, TaskDelayTime delayTime) {
		super(executable, delayTime);
	}

	public AsyncTask(Function<TaskContext<TSupply>, TResult> executable) {
		super(executable);
	}


	@Override
	public void run(TaskContext<TSupply> taskContext) {
		TaskLoop.getInstance().getExecutorService().schedule(() -> {
			TResult result = this.getExecutable().apply(taskContext);

			Task<TResult, ?> nextTask = this.getNextTask();

			if (nextTask == null) {
				return;
			}

			TaskContext<TResult> nextTaskContext = new TaskContext<>(taskContext.getScriptContext(), result);
			if (nextTask instanceof AsyncTask<?, ?>) {
				nextTask.run(nextTaskContext);
			} else {
				ContextualizedSyncTaskDefinition resultContext = new ContextualizedSyncTaskDefinition(
						(SyncTask<?, ?>) nextTask,
						nextTaskContext
				);
				TaskLoop.getInstance().getAsyncTaskQueue().offer(resultContext);
			}
		}, this.getDelayTime().toMillis().getDelay(), TimeUnit.MILLISECONDS);
	}

}
