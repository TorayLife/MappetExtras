package toraylife.mappetextras.modules.utils.tasks;

import mchorse.mappet.api.scripts.user.IScriptEvent;

import java.util.function.Function;

public abstract class Task<TConsume, TResult> {
	public enum Type {
		SYNC, ASYNC
	}

	protected Type type;
	protected Task<Void, ?> initTask;
	protected final TaskDelayTime timeoutDelay;

	protected volatile Task<TResult, ?> nextTask;


	protected Task(Type type,
	               Task<Void, ?> initTask,
	               TaskDelayTime timeoutDelay) {
		this.type = type;
		this.initTask = initTask;
		this.timeoutDelay = timeoutDelay;
	}


	public abstract void schedule(TaskContext<TConsume> taskContext);


	public <TNextResult> Task<TResult, TNextResult> then(Function<TaskContext<TResult>, TNextResult> taskExecutable) {
		SyncTask<TResult, TNextResult> nextTask = new SyncTask<>(this.initTask, TaskDelayTime.ZERO_TICKS, taskExecutable);

		this.setNextTask(nextTask);

		return nextTask;
	}

	public <TNextResult> Task<TResult, TNextResult> thenAsync(Function<TaskContext<TResult>, TNextResult> taskExecutable) {
		Task<TResult, TNextResult> nextTask = new AsyncTask<>(this.initTask, TaskDelayTime.ZERO_MILLIS, taskExecutable);

		this.setNextTask(nextTask);

		return nextTask;
	}


	public <TAccumulator> LoopTaskBuilder<TResult, TAccumulator> thenLoop(int iterationCount) {
		return new LoopTaskBuilder<>(this, iterationCount, TaskDelayTime.ZERO_TICKS);
	}


	public DelayedTaskBuilder<TResult> thenWaitTicks(int ticks) {
		return new DelayedTaskBuilder<>(this, new TaskDelayTime(ticks, TaskDelayTime.Unit.TICKS));
	}

	public DelayedTaskBuilder<TResult> thenWaitMillis(int millis) {
		return new DelayedTaskBuilder<>(this, new TaskDelayTime(millis, TaskDelayTime.Unit.MILLIS));
	}


	public void run(IScriptEvent context) {
		if (this.initTask != this) {
			this.initTask.run(context);
			return;
		}

		this.schedule(new TaskContext<>(this, context, null));
	}

	public void run() {
		if (this.initTask != this) {
			this.initTask.run();
			return;
		}

		this.schedule(new TaskContext<>(this, null, null));
	}


	public Task<TResult, ?> getNextTask() {
		return nextTask;
	}

	public void setNextTask(Task<TResult, ?> nextTask) {
		this.nextTask = nextTask;
	}

	public Task<Void, ?> getInitTask() {
		return initTask;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	public void setInitTask(Task initTask) {
		this.initTask = (Task<Void, ?>) initTask;
	}

}
