package toraylife.mappetextras.modules.utils.tasks;

import java.util.function.Function;

public class DelayedTaskBuilder<TConsume> {
	private final Task<?, TConsume> previousTask;

	private final TaskDelayTime delayTime;


	public DelayedTaskBuilder(Task<?, TConsume> previousTask, TaskDelayTime delayTime) {
		this.previousTask = previousTask;
		this.delayTime = delayTime;
	}


	public <TNextResult> Task<TConsume, TNextResult> then(TaskExecutable<TConsume, TNextResult> taskExecutable) {
		Task<Void, ?> initTask = null;
		if (this.previousTask != null) {
			initTask = this.previousTask.getInitTask();
		}

		Task<TConsume, TNextResult> nextTask = new SyncTask<>(initTask, delayTime, taskExecutable);
		if (initTask == null) {
			nextTask.setInitTask(nextTask);
		}

		if (this.previousTask != null) {
			this.previousTask.setNextTask(nextTask);
		}

		return nextTask;
	}


	public <TNextResult> Task<TConsume, TNextResult> thenAsync(TaskExecutable<TConsume, TNextResult> taskExecutable) {
		Task<Void, ?> initTask = null;
		if (this.previousTask != null) {
			initTask = this.previousTask.getInitTask();
		}

		Task<TConsume, TNextResult> nextTask = new AsyncTask<>(initTask, delayTime, taskExecutable);
		if (initTask == null) {
			nextTask.setInitTask(nextTask);
		}

		if (this.previousTask != null) {
			this.previousTask.setNextTask(nextTask);
		}

		return nextTask;
	}


	public LoopTaskBuilder<TConsume, Object> thenLoop(int iterationCount) {
		return new LoopTaskBuilder<>(this.previousTask, iterationCount, this.delayTime);
	}

}
