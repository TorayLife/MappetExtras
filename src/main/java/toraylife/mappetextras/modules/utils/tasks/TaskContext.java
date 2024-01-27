package toraylife.mappetextras.modules.utils.tasks;

import mchorse.mappet.api.scripts.user.IScriptEvent;

public class TaskContext<TPreviousResult> {
	protected Task<TPreviousResult, ?> task;
	protected IScriptEvent scriptContext;
	protected TPreviousResult resultValue;


	public TaskContext(Task<TPreviousResult, ?> task,
	                   IScriptEvent scriptContext,
	                   TPreviousResult resultValue) {
		this.task = task;
		this.scriptContext = scriptContext;
		this.resultValue = resultValue;
	}

	public TPreviousResult getResult() {
		return resultValue;
	}

	public IScriptEvent getScriptContext() {
		return scriptContext;
	}


	public <TValue> TaskResult<TValue> result(TValue result) {
		return new ValueTaskResult<>(result);
	}

	public <TResult> TaskResult<TResult> delegate(Task<?, TResult> delegateTask) {
		return new DelegateTaskResult<>(delegateTask);
	}

	public <TExpectedResult> TaskResult<TExpectedResult> waitForResolve() {
		return new UnresolvedTaskResult<>();
	}

	@SuppressWarnings("unchecked")
	public <TResult> void resolve(TResult resultValue) {
		Task<TResult, ?> nextTask = (Task<TResult, ?>) task.getNextTask();
		TaskContext<TResult> nextTaskContext = new TaskContext<>(nextTask, scriptContext, resultValue);

		if (nextTask instanceof AsyncTask) {
			nextTask.schedule(nextTaskContext);
		} else {
			SyncTaskScheduleDef scheduleDef = new SyncTaskScheduleDef(nextTask, nextTaskContext);
			TaskLoop.getInstance().getSyncTaskScheduleQueue().offer(scheduleDef);
		}
	}
}
