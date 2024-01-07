package toraylife.mappetextras.modules.utils.tasks;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

public abstract class LoopTask<TResult, TAccumulator> extends Task<TResult, TResult> {
	protected final Function<LoopTaskContext<TResult, TAccumulator>, TAccumulator> executable;
	protected final TaskDelayTime defaultIntervalDelay;


	protected volatile TAccumulator accumulator;
	protected volatile long iterationCount;
	protected final AtomicLong currentIterationIndex = new AtomicLong(-1);
	protected volatile TaskDelayTime nextIntervalDelay;


	protected LoopTask(Type type,
	                   Task<Void, ?> initTask,
	                   TaskDelayTime timeoutDelay,
	                   Function<LoopTaskContext<TResult, TAccumulator>, TAccumulator> executable,
	                   long iterationCount,
	                   TaskDelayTime defaultIntervalDelay,
	                   TAccumulator initAccumulator) {
		super(type, initTask, timeoutDelay);
		this.executable = executable;
		this.iterationCount = iterationCount;
		this.defaultIntervalDelay = defaultIntervalDelay;
		this.accumulator = initAccumulator;
	}

}
