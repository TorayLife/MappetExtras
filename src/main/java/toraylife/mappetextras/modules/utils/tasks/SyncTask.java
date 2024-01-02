package toraylife.mappetextras.modules.utils.tasks;

import mchorse.mappet.CommonProxy;
import mchorse.mappet.utils.RunnableExecutionFork;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Function;

public class SyncTask<TConsume, TResult> extends Task<TConsume, TResult> {

	public SyncTask(Function<TaskContext<TConsume>, TaskResult<TResult>> executable, TaskDelayTime delayTime) {
		super(executable, delayTime);
	}

	public SyncTask(Function<TaskContext<TConsume>, TaskResult<TResult>> executable) {
		super(executable);
	}

	public SyncTask(Task<Void, ?> initTask, Function<TaskContext<TConsume>, TaskResult<TResult>> executable, TaskDelayTime delayTime) {
		super(initTask, executable, delayTime);
	}

	public SyncTask(Task<Void, ?> initTask, Function<TaskContext<TConsume>, TaskResult<TResult>> executable) {
		super(initTask, executable);
	}


	@Override
	public void schedule(TaskContext<TConsume> taskContext) {
		Runnable scriptRunnable = () -> {
			TaskResult<TResult> taskResult = getExecutable().apply(taskContext);


			if (taskResult == null || taskResult instanceof ValueTaskResult<?>) {
				ValueTaskResult<TResult> valueResult = (ValueTaskResult<TResult>) taskResult;
				TResult resultValue = taskResult != null ? valueResult.getValue() : null;
				this.getNextTask().schedule(
						new TaskContext<>(this.getNextTask(), taskContext.getScriptContext(), resultValue)
				);
			}
			else if (taskResult instanceof DelegateTaskResult<?>) {
				DelegateTaskResult<TResult> delegateResult = (DelegateTaskResult<TResult>) taskResult;
				Task<?, TResult> delegateTask = delegateResult.getDelegateTask();
				delegateTask.setNextTask(this.getNextTask());
				delegateTask.getInitTask().schedule(
						new TaskContext<>(delegateTask.getInitTask(), taskContext.getScriptContext(), null)
				);
			}
			else {
				throw new NotImplementedException("Unknown Task result type");
			}
		};

		CommonProxy.eventHandler.addExecutable(
				new RunnableExecutionFork(getDelayTime().toTicks().getDelay(), scriptRunnable));
	}

}
