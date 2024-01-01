package toraylife.mappetextras.modules.utils.tasks;

import mchorse.mappet.CommonProxy;
import mchorse.mappet.utils.RunnableExecutionFork;

import java.util.function.Function;

public class SyncTask<TSupply, TResult> extends Task<TSupply, TResult> {

	public SyncTask(Function<TaskContext<TSupply>, TResult> executable, TaskDelayTime delayTime) {
		super(executable, delayTime);
	}

	public SyncTask(Function<TaskContext<TSupply>, TResult> executable) {
		super(executable);
	}

	public SyncTask(Task<Void, ?> initTask, Function<TaskContext<TSupply>, TResult> executable, TaskDelayTime delayTime) {
		super(initTask, executable, delayTime);
	}

	public SyncTask(Task<Void, ?> initTask, Function<TaskContext<TSupply>, TResult> executable) {
		super(initTask, executable);
	}


	@Override
	public void run(TaskContext<TSupply> taskContext) {
		Runnable scriptRunnable = () -> {
			TResult result = getExecutable().apply(taskContext);

			Task<TResult, ?> nextTask = this.getNextTask();
			TaskContext<TResult> nextTaskContext = new TaskContext<>(taskContext.getScriptContext(), result);

			if (nextTask != null) {
				nextTask.run(nextTaskContext);
			}
		};

		CommonProxy.eventHandler.addExecutable(
				new RunnableExecutionFork(getDelayTime().toTicks().getDelay(), scriptRunnable));
	}

}
