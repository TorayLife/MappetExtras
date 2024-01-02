package toraylife.mappetextras.modules.utils.tasks;

import mchorse.mappet.api.scripts.user.IScriptEvent;

import java.util.function.Function;

public abstract class Task<TConsume, TResult> {
	private Task<Void, ?> initTask = null;
	private Task<TResult, ?> nextTask;
	private final Function<TaskContext<TConsume>, TaskResult<TResult>> executable;
	private TaskDelayTime delayTime = new TaskDelayTime(0, TaskDelayTime.Unit.MILLIS);

	protected Task(Function<TaskContext<TConsume>, TaskResult<TResult>> executable) {
		this.executable = executable;
	}

	protected Task(Function<TaskContext<TConsume>, TaskResult<TResult>> executable,
	               TaskDelayTime delayTime) {
		this(executable);
		this.delayTime = delayTime;
	}

	protected Task(Task<Void, ?> initTask,
	               Function<TaskContext<TConsume>, TaskResult<TResult>> executable) {
		this(executable);
		this.initTask = initTask;
	}

	protected Task(Task<Void, ?> initTask,
	               Function<TaskContext<TConsume>, TaskResult<TResult>> executable,
	               TaskDelayTime delayTime) {
		this(executable, delayTime);
		this.initTask = initTask;
	}


	public abstract void schedule(TaskContext<TConsume> taskContext);


	public <TNextResult> Task<TResult, TNextResult> then(Function<TaskContext<TResult>, TaskResult<TNextResult>> taskExecutable) {
		SyncTask<TResult, TNextResult> nextTask = new SyncTask<>(this.initTask, taskExecutable);

		this.setNextTask(nextTask);

		return nextTask;
	}

	public <TNextResult> Task<TResult, TNextResult> thenAsync(Function<TaskContext<TResult>, TaskResult<TNextResult>> taskExecutable) {
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

	public void run(IScriptEvent context) {
		if (this.initTask != this) {
			this.initTask.run(context);
			return;
		}

		this.schedule(new TaskContext<>(this, context, null));
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

	public TaskDelayTime getDelayTime() {
		return this.delayTime;
	}

	public Function<TaskContext<TConsume>, TaskResult<TResult>> getExecutable() {
		return executable;
	}

}
