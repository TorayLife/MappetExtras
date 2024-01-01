package toraylife.mappetextras.modules.utils.tasks;

import java.util.function.Function;

public class DelayedTaskBuilder<TSupply> {
	private final Task<?, TSupply> previousTask;
	private final TaskDelayTime delayTime;


	public DelayedTaskBuilder(Task<?, TSupply> previousTask, TaskDelayTime delayTime) {
		this.previousTask = previousTask;
		this.delayTime = delayTime;
	}


	public <TNextResult> Task<TSupply, TNextResult> then(Function<TaskContext<TSupply>, TNextResult> taskExecutable) {
		Task<TSupply, TNextResult> nextTask = new SyncTask<>(this.previousTask.getInitTask(), taskExecutable, delayTime);

		this.previousTask.setNextTask(nextTask);

		return nextTask;
	}

	public <TNextResult> Task<TSupply, TNextResult> thenAsync(Function<TaskContext<TSupply>, TNextResult> taskExecutable) {
		Task<TSupply, TNextResult> nextTask = new AsyncTask<>(this.previousTask.getInitTask(), taskExecutable, delayTime);

		this.previousTask.setNextTask(nextTask);

		return nextTask;
	}

}
