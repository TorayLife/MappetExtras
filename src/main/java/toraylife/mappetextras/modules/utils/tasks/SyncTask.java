package toraylife.mappetextras.modules.utils.tasks;

import mchorse.mappet.CommonProxy;
import mchorse.mappet.utils.RunnableExecutionFork;

import java.util.function.Function;

public class SyncTask<TConsume, TResult> extends Task<TConsume, TResult> {

	public SyncTask(Function<TaskContext<TConsume>, TResult> executable, TaskDelayTime delayTime) {
		super(executable, delayTime);
	}

	public SyncTask(Function<TaskContext<TConsume>, TResult> executable) {
		super(executable);
	}

	public SyncTask(Task<Void, ?> initTask, Function<TaskContext<TConsume>, TResult> executable, TaskDelayTime delayTime) {
		super(initTask, executable, delayTime);
	}

	public SyncTask(Task<Void, ?> initTask, Function<TaskContext<TConsume>, TResult> executable) {
		super(initTask, executable);
	}


	@Override
	@SuppressWarnings("unchecked")
	public void schedule(TaskContext<TConsume> taskContext) {
		Runnable scriptRunnable = () -> {
			TResult resultValue = this.getExecutable().apply(taskContext);
			TaskResult taskResult = taskContext.getResult();

			Task<TResult, ?> nextTask = this.getNextTask();

			if (taskResult instanceof DelegateTaskResult<?>) {
				DelegateTaskResult<TResult> delegateResult = (DelegateTaskResult<TResult>) taskResult;
				Task<?, TResult> delegateTask = delegateResult.getDelegateTask();

				Task<Void, ?> actualNextTask = delegateTask.getInitTask();
				delegateTask.setInitTask(this.getInitTask());
				delegateTask.setNextTask(nextTask);

				actualNextTask.schedule(
						new TaskContext<>(actualNextTask, taskContext.getScriptContext(), null)
				);
			}
			else if (!(taskResult instanceof UnresolvedTaskResult) && nextTask != null) {
				nextTask.schedule(new TaskContext<>(nextTask, taskContext.getScriptContext(), resultValue));
			}
		};

		CommonProxy.eventHandler.addExecutable(
				new RunnableExecutionFork(getDelayTime().toTicks().getDelay(), scriptRunnable));
	}

}
