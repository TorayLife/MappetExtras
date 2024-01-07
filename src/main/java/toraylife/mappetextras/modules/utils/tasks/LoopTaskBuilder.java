package toraylife.mappetextras.modules.utils.tasks;

import java.util.function.Function;

public class LoopTaskBuilder<TConsume, TAccumulator> {
	private final Task<?, TConsume> previousTask;

	private final long iterationCount;
	private final TaskDelayTime timeoutDelay;
	private TaskDelayTime defaultIntervalDelay = TaskDelayTime.ZERO_TICKS;
	private TAccumulator accumulator;

	public LoopTaskBuilder(Task<?, TConsume> previousTask, long iterationCount, TaskDelayTime timeoutDelay) {
		this.previousTask = previousTask;
		this.iterationCount = iterationCount;
		this.timeoutDelay = timeoutDelay;
	}

	public LoopTaskBuilder<TConsume, TAccumulator> delayTicks(int ticks) {
		this.defaultIntervalDelay = new TaskDelayTime(ticks, TaskDelayTime.Unit.TICKS);
		return this;
	}

	public LoopTaskBuilder<TConsume, TAccumulator> delayMillis(int millis) {
		this.defaultIntervalDelay = new TaskDelayTime(millis, TaskDelayTime.Unit.MILLIS);
		return this;
	}

	public LoopTaskBuilder<TConsume, TAccumulator> accumulator(TAccumulator accumulator) {
		this.accumulator = accumulator;
		return this;
	}

	public Task<TConsume, TConsume> iterateAsync(Function<LoopTaskContext<TConsume, TAccumulator>, TAccumulator> taskExecutable) {
		Task<Void, ?> initTask = null;
		if (this.previousTask != null) {
			initTask = this.previousTask.getInitTask();
		}

		Task<TConsume, TConsume> nextTask = new AsyncLoopTask<>(
				initTask, taskExecutable, this.timeoutDelay,
				this.iterationCount, this.defaultIntervalDelay, this.accumulator
		);
		if (initTask == null) {
			nextTask.setInitTask(nextTask);
		}

		if (this.previousTask != null) {
			this.previousTask.setNextTask(nextTask);
		}

		return nextTask;
	}

}
