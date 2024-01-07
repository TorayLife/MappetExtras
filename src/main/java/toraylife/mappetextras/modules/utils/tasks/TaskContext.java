package toraylife.mappetextras.modules.utils.tasks;

import mchorse.mappet.api.scripts.user.IScriptEvent;

public class TaskContext<TPreviousResult> {
	protected Task<TPreviousResult, ?> task;
	protected IScriptEvent scriptContext;
	protected TPreviousResult resultValue;
	protected TaskResult result;


	public TaskContext(Task<TPreviousResult, ?> task,
	                   IScriptEvent scriptContext,
	                   TPreviousResult resultValue) {
		this.task = task;
		this.scriptContext = scriptContext;
		this.resultValue = resultValue;
	}


	TaskResult getCurrentResult() {
		return result;
	}


	public TPreviousResult getResult() {
		return resultValue;
	}

	public IScriptEvent getScriptContext() {
		return scriptContext;
	}


	public <TResult> TResult delegate(Task<?, TResult> delegateTask) {
		this.result = new DelegateTaskResult<>(delegateTask);
		return null;
	}

	public <TExpectedResult> TExpectedResult waitForResolve() {
		this.result = new UnresolvedTaskResult();
		return null;
	}

	@SuppressWarnings("unchecked")
	public <TResult> void resolve(TResult resultValue) {
		if (!(this.result instanceof UnresolvedTaskResult)) {
			throw new IllegalStateException("Task should wait for resolve before actual resolving");
		}

		Task<TResult, ?> nextTask = (Task<TResult, ?>) task.getNextTask();
		TaskContext<TResult> nextTaskContext = new TaskContext<>(nextTask, scriptContext, resultValue);

		if (nextTask instanceof AsyncTask) {
			nextTask.schedule(nextTaskContext);
		} else {
			SyncTaskScheduleDefinition scheduleDef = new SyncTaskScheduleDefinition(nextTask, nextTaskContext);
			TaskLoop.getInstance().getSyncTaskScheduleDefinitionQueue().offer(scheduleDef);
		}
	}
}
