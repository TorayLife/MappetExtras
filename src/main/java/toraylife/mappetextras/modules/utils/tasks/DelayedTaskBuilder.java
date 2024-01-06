package toraylife.mappetextras.modules.utils.tasks;

import java.util.function.Function;

public class DelayedTaskBuilder<TConsume> {
	private final Task<?, TConsume> previousTask;
	private final TaskDelayTime delayTime;


	public DelayedTaskBuilder(Task<?, TConsume> previousTask, TaskDelayTime delayTime) {
		this.previousTask = previousTask;
		this.delayTime = delayTime;
	}


	public <TNextResult> Task<TConsume, TNextResult> then(Function<TaskContext<TConsume>, TNextResult> taskExecutable) {
		Task<Void, ?> initTask = null;
		if (this.previousTask != null) {
			initTask = this.previousTask.getInitTask();
		}

		Task<TConsume, TNextResult> nextTask = new SyncTask<>(initTask, taskExecutable, delayTime);
		if (initTask == null) {
			nextTask.setInitTask(nextTask);
		}

		if (this.previousTask != null) {
			this.previousTask.setNextTask(nextTask);
		}

		return nextTask;
	}


	public <TNextResult> Task<TConsume, TNextResult> thenAsync(Function<TaskContext<TConsume>, TNextResult> taskExecutable) {
		Task<Void, ?> initTask = null;
		if (this.previousTask != null) {
			initTask = this.previousTask.getInitTask();
		}

		Task<TConsume, TNextResult> nextTask = new AsyncTask<>(initTask, taskExecutable, delayTime);
		if (initTask == null) {
			nextTask.setInitTask(nextTask);
		}

		if (this.previousTask != null) {
			this.previousTask.setNextTask(nextTask);
		}

		return nextTask;
	}

}
