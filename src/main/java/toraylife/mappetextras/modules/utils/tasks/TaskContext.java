package toraylife.mappetextras.modules.utils.tasks;

import mchorse.mappet.api.scripts.user.IScriptEvent;

public class TaskContext<TPreviousResult> {
	private final Task<TPreviousResult, ?> task;
	private final IScriptEvent scriptContext;
	private final TPreviousResult previousResult;
	private TaskResult result;


	public TaskContext(Task<TPreviousResult, ?> task, IScriptEvent scriptContext, TPreviousResult previousResult) {
		this.task = task;
		this.scriptContext = scriptContext;
		this.previousResult = previousResult;
	}


	public TPreviousResult getPreviousResult() {
		return previousResult;
	}

	public IScriptEvent getScriptContext() {
		return scriptContext;
	}

	TaskResult getResult() {
		return result;
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
			SyncTaskScheduleDefinition resultContext =
					new SyncTaskScheduleDefinition((SyncTask<?, ?>) nextTask, nextTaskContext);
			TaskLoop.getInstance().getSyncTaskScheduleDefinitionQueue().offer(resultContext);
		}
	}

}
