package toraylife.mappetextras.modules.utils.tasks;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class AsyncLoopTask<TResult, TAccumulator> extends LoopTask<TResult, TAccumulator> {

	public AsyncLoopTask(Task<Void, ?> initTask,
	                     Function<LoopTaskContext<TResult, TAccumulator>, TAccumulator> executable,
	                     TaskDelayTime timeoutDelay,
	                     long iterationCount,
	                     TaskDelayTime defaultIntervalDelay,
	                     TAccumulator initAccumulator) {
		super(Type.ASYNC, initTask, timeoutDelay, executable, iterationCount, defaultIntervalDelay, initAccumulator);
	}


	@Override
	@SuppressWarnings("unchecked")
	public void schedule(TaskContext<TResult> taskContext) {
		long currentIterationIndex = this.currentIterationIndex.incrementAndGet();

		LoopTaskContext<TResult, TAccumulator> iterationContext;
		int scheduleDelayMillis;
		if (!(taskContext instanceof LoopTaskContext<?, ?>)) {
			iterationContext = new LoopTaskContext<>(
					taskContext.task, taskContext.scriptContext, taskContext.resultValue, this.accumulator,
					this.iterationCount, currentIterationIndex, TaskDelayTime.ZERO_MILLIS, this.defaultIntervalDelay
			);
			scheduleDelayMillis = timeoutDelay.toMillis().getDelay();
		}
		else {
			iterationContext = (LoopTaskContext<TResult, TAccumulator>) taskContext;
			scheduleDelayMillis = iterationContext.getPreviousDelayMillis();
		}

		if (iterationContext.getIndex() >= iterationContext.getIterationCount()) {
			TaskContext<TResult> nextTaskContext =
					new TaskContext<>(this.nextTask, taskContext.scriptContext, taskContext.resultValue);

			scheduleNextTask(this.nextTask, nextTaskContext);
			return;
		}
		
		TaskLoop.getInstance().getExecutorService().schedule(() -> {
			TAccumulator accumulator = this.executable.apply(iterationContext);
			TaskResult taskResult = iterationContext.getCurrentResult();

			this.accumulator = accumulator;
			this.iterationCount = iterationContext.iterationCount;
			this.nextIntervalDelay = iterationContext.nextDelay;

			if (taskResult instanceof DelegateTaskResult<?>) {
				scheduleDelegateTask(iterationContext, (DelegateTaskResult<TResult>) taskResult);
			}
			else if (!(taskResult instanceof UnresolvedTaskResult)) {
				LoopTaskContext<TResult, TAccumulator> nextIterationContext =
						iterationContext.next(accumulator, this.defaultIntervalDelay, currentIterationIndex + 1);

				this.nextTask.schedule(nextIterationContext);
			}
		}, scheduleDelayMillis, TimeUnit.MILLISECONDS);
	}

	private void scheduleDelegateTask(TaskContext<TResult> taskContext, DelegateTaskResult<TResult> taskResult) {
		Task<?, TResult> delegateTask = taskResult.getDelegateTask();

		delegateTask.nextTask = this;

		TaskContext<Void> delegateTaskContext = new TaskContext<>(delegateTask.initTask, taskContext.scriptContext, null);
		scheduleNextTask(delegateTask.initTask, delegateTaskContext);
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	private void scheduleNextTask(Task nextTask, TaskContext nextTaskContext) {
		if (nextTask.type == Type.ASYNC) {
			nextTask.schedule(nextTaskContext);
		}
		else {
			SyncTaskScheduleDefinition scheduleDef = new SyncTaskScheduleDefinition(nextTask, nextTaskContext);

			TaskLoop.getInstance().getSyncTaskScheduleDefinitionQueue().offer(scheduleDef);
		}
	}

}
