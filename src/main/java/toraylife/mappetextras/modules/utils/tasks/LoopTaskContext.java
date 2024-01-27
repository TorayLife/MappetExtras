package toraylife.mappetextras.modules.utils.tasks;

import mchorse.mappet.api.scripts.user.IScriptEvent;

public class LoopTaskContext<TResult, TAccumulator> extends TaskContext<TResult> {
	protected final TAccumulator accumulator;
	protected long iterationCount;
	protected final long currentIterationIndex;
	protected final TaskDelayTime previousDelay;
	protected TaskDelayTime nextDelay;


	public LoopTaskContext(Task<TResult, ?> task,
	                       IScriptEvent scriptContext,
	                       TResult resultValue,
	                       TAccumulator accumulator,
	                       long iterationCount,
	                       long currentIterationIndex,
	                       TaskDelayTime previousDelay,
	                       TaskDelayTime nextDelay) {
		super(task, scriptContext, resultValue);
		this.accumulator = accumulator;
		this.iterationCount = iterationCount;
		this.currentIterationIndex = currentIterationIndex;
		this.previousDelay = previousDelay;
		this.nextDelay = nextDelay;
	}


	LoopTaskContext<TResult, TAccumulator> next(TAccumulator accumulator,
	                                            TaskDelayTime defaultIntervalDelay,
	                                            long currentIterationIndex) {
		return new LoopTaskContext<>(
				this.task, this.scriptContext, this.resultValue, accumulator,
				this.iterationCount, currentIterationIndex,
				this.nextDelay, defaultIntervalDelay
		);
	}


	public TAccumulator getAccumulator() {
		return accumulator;
	}

	public long getIterationCount() {
		return iterationCount;
	}

	public void setIterationCount(long iterationCount) {
		this.iterationCount = iterationCount;
	}

	public long getIndex() {
		return currentIterationIndex;
	}

	public int getPreviousDelayTicks() {
		return previousDelay.toTicks().getDelay();
	}

	public int getPreviousDelayMillis() {
		return previousDelay.toMillis().getDelay();
	}

	public void setNextDelayTicks(int ticks) {
		this.nextDelay = new TaskDelayTime(ticks, TaskDelayTime.Unit.TICKS);
	}

	public void setNextDelayMillis(int millis) {
		this.nextDelay = new TaskDelayTime(millis, TaskDelayTime.Unit.MILLIS);
	}

}
