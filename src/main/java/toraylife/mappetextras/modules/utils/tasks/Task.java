package toraylife.mappetextras.modules.utils.tasks;

import mchorse.mappet.api.scripts.user.IScriptEvent;

import java.util.function.Function;

public abstract class Task<TSupply, TResult> {
	private Task<Void, ?> initTask = null;
	private Task<TResult, ?> nextTask;
	private final Function<TaskContext<TSupply>, TResult> executable;
	private TaskDelayTime delayTime = new TaskDelayTime(0, TaskDelayTime.Unit.MILLIS);

	protected Task(Task<Void, ?> initTask,
	               Function<TaskContext<TSupply>, TResult> executable,
	               TaskDelayTime delayTime) {
		this.initTask = initTask;
		this.executable = executable;
		this.delayTime = delayTime;
	}

	protected Task(Task<Void, ?> initTask,
	               Function<TaskContext<TSupply>, TResult> executable) {
		this.initTask = initTask;
		this.executable = executable;
	}

	protected Task(Function<TaskContext<TSupply>, TResult> executable,
	               TaskDelayTime delayTime) {
		this.executable = executable;
		this.delayTime = delayTime;
	}

	protected Task(Function<TaskContext<TSupply>, TResult> executable) {
		this.executable = executable;
	}


	public abstract void run(TaskContext<TSupply> taskContext);


	public <TNextResult> Task<TResult, TNextResult> then(Function<TaskContext<TResult>, TNextResult> taskExecutable) {
		Task<TResult, TNextResult> nextTask = new SyncTask<>(this.initTask, taskExecutable);

		this.setNextTask(nextTask);

		return nextTask;
	}

	public <TNextResult> Task<TResult, TNextResult> thenAsync(Function<TaskContext<TResult>, TNextResult> taskExecutable) {
		Task<TResult, TNextResult> nextTask = new AsyncTask<>(this.initTask, taskExecutable);

		this.setNextTask(nextTask);

		return nextTask;
	}

	public DelayedTaskBuilder<TResult> thenWaitTicks(int ticks) {
		return new DelayedTaskBuilder<>(this, new TaskDelayTime(ticks, TaskDelayTime.Unit.TICKS));
	}

	public DelayedTaskBuilder<TResult> thenWaitMillis(int millis) {
		return new DelayedTaskBuilder<>(this, new TaskDelayTime(millis, TaskDelayTime.Unit.MILLIS));
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

	public void setInitTask(Task<Void, ?> initTask) {
		this.initTask = initTask;
	}

	public TaskDelayTime getDelayTime() {
		return this.delayTime;
	}

	public Function<TaskContext<TSupply>, TResult> getExecutable() {
		return executable;
	}

	public void run(IScriptEvent context) {
		if (this.initTask != this) {
			this.initTask.run(context);
			return;
		}

		this.run(new TaskContext<>(context, null));
	}
}
