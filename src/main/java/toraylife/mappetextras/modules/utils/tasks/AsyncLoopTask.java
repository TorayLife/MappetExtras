package toraylife.mappetextras.modules.utils.tasks;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class AsyncLoopTask<TResult, TAccumulator> extends LoopTask<TResult, TAccumulator> {

	public AsyncLoopTask(Task<Void, ?> initTask,
	                     Function<LoopTaskContext<TResult, TAccumulator>, TaskResult<TAccumulator>> executable,
	                     TaskDelayTime timeoutDelay,
	                     long initIterationCount,
	                     TaskDelayTime defaultIntervalDelay,
	                     TAccumulator initAccumulator) {
		super(Type.ASYNC, initTask, timeoutDelay, executable, initIterationCount, defaultIntervalDelay, initAccumulator);
	}


	@Override
	public void schedule(TaskContext<TResult> taskContext) {
		long currentIterationIndex = this.currentIterationIndex.incrementAndGet();

		LoopTaskContext<TResult, TAccumulator> currentIterationContext = resolveTaskContext(taskContext, currentIterationIndex);
		int scheduleDelayMillis = getScheduleDelayMillis(currentIterationIndex);

		if (currentIterationIndex >= this.iterationCount) {
			if (this.nextTask != null) {
				scheduleNextTask(taskContext);
			}
			return;
		}

		TaskLoop.getInstance().getExecutorService().schedule(() -> {
			Object objectIterResult = this.executable.apply(currentIterationContext);
			TaskResult<TAccumulator> taskIterResult = TaskResult.from(objectIterResult);

			this.iterationCount = currentIterationContext.iterationCount;
			this.nextIntervalDelay = currentIterationContext.nextDelay;

			if (taskIterResult instanceof DelegateTaskResult<?>) {
				scheduleDelegateTask(currentIterationContext, (DelegateTaskResult<TAccumulator>) taskIterResult);
			}
			else if (taskIterResult instanceof AccumulateTaskResult<?>) {
				this.accumulator = ((AccumulateTaskResult<TAccumulator>) taskIterResult).getAccumulator();
				resumeIterationWithCurrentAccumulator(currentIterationContext, currentIterationIndex);
			}
			else if (taskIterResult instanceof EmptyTaskResult) {
				resumeIterationWithCurrentAccumulator(currentIterationContext, currentIterationIndex);
			}
			else if (taskIterResult instanceof ValueTaskResult<?>) {
				scheduleNextTask(taskContext);
			}
		}, scheduleDelayMillis, TimeUnit.MILLISECONDS);
	}

	private LoopTaskContext<TResult, TAccumulator> resolveTaskContext(TaskContext<TResult> taskContext,
	                                                                  long currentIterationIndex) {
		if (taskContext instanceof LoopTaskContext<?, ?>) {
			return (LoopTaskContext<TResult, TAccumulator>) taskContext;
		}
		else {
			return new LoopTaskContext<>(
					taskContext.task, taskContext.scriptContext, taskContext.resultValue, this.accumulator,
					this.iterationCount, currentIterationIndex, this.nextIntervalDelay, this.defaultIntervalDelay
			);
		}
	}

	private int getScheduleDelayMillis(long currentIterationIndex) {
		if (currentIterationIndex == 0) {
			return this.timeoutDelay.toMillis().getDelay();
		}
		else {
			return this.nextIntervalDelay.toMillis().getDelay();
		}
	}

	private void resumeIterationWithCurrentAccumulator(LoopTaskContext<TResult, TAccumulator> currentIterationContext,
	                                                   long currentIterationIndex) {
		LoopTaskContext<TResult, TAccumulator> nextIterationContext =
				currentIterationContext.next(this.accumulator, this.defaultIntervalDelay, currentIterationIndex + 1);
		this.schedule(nextIterationContext);
	}

	private void scheduleNextTask(TaskContext<TResult> taskContext) {
		TaskContext<TAccumulator> nextTaskContext =
				new TaskContext<>(this.nextTask, taskContext.scriptContext, this.accumulator);
		scheduleTask(this.nextTask, nextTaskContext);
	}

	@SuppressWarnings("unchecked")
	private void scheduleDelegateTask(TaskContext<TResult> taskContext, DelegateTaskResult<TAccumulator> taskResult) {
		Task<?, TAccumulator> delegateTask = taskResult.getDelegateTask();

		delegateTask.nextTask = (Task<TAccumulator, ?>) this;

		TaskContext<Void> delegateTaskContext = new TaskContext<>(delegateTask.initTask, taskContext.scriptContext, null);
		scheduleTask(delegateTask.initTask, delegateTaskContext);
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	private void scheduleTask(Task nextTask, TaskContext nextTaskContext) {
		if (nextTask.type == Type.ASYNC) {
			nextTask.schedule(nextTaskContext);
		}
		else {
			SyncTaskScheduleDef scheduleDef = new SyncTaskScheduleDef(nextTask, nextTaskContext);

			TaskLoop.getInstance().getSyncTaskScheduleQueue().offer(scheduleDef);
		}
	}
}
