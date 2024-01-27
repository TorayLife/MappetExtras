package toraylife.mappetextras.modules.utils.tasks;

import java.util.function.Function;

public abstract class RegularTask<TConsume, TResult> extends Task<TConsume, TResult> {
	protected final TaskExecutable<TConsume, TResult> executable;

	protected RegularTask(Task.Type type,
	                      Task<Void, ?> initTask,
	                      TaskDelayTime timeoutDelay,
	                      TaskExecutable<TConsume, TResult> executable) {
		super(type, initTask, timeoutDelay);
		this.executable = executable;
	}

}
