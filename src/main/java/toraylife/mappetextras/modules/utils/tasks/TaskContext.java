package toraylife.mappetextras.modules.utils.tasks;

import mchorse.mappet.api.scripts.user.IScriptEvent;

public class TaskContext<TPreviousResult> {
	private final Task<TPreviousResult, ?> task;
	private final IScriptEvent scriptContext;
	private final TPreviousResult result;


	public TaskContext(Task<TPreviousResult, ?> task, IScriptEvent scriptContext, TPreviousResult previousResult) {
		this.task = task;
		this.scriptContext = scriptContext;
		this.result = previousResult;
	}


	public TPreviousResult getPreviousResult() {
		return result;
	}

	public IScriptEvent getScriptContext() {
		return scriptContext;
	}


	public <TResult> TaskResult<TResult> result(TResult result) {
		return new ValueTaskResult<>(result);
	}

	public <TResult> TaskResult<TResult> delegate(Task<?, TResult> delegateTask) {
		return new DelegateTaskResult<>(delegateTask);
	}

	public <TExpectedResult> TaskResult<TExpectedResult> waitForResolve() {
		return new UnresolvedTaskResult<>();
	}

	@SuppressWarnings("unchecked")
	public <TResult> void resolve(TResult result) {
		Task<TResult, ?> nextTask = (Task<TResult, ?>) task.getNextTask();
		TaskContext<TResult> nextTaskContext = new TaskContext<>(nextTask, scriptContext, result);

		if (nextTask instanceof AsyncTask) {
			nextTask.schedule(nextTaskContext);
		} else {
			SyncTaskScheduleDefinition resultContext = new SyncTaskScheduleDefinition(
					(SyncTask<?, ?>) nextTask,
					nextTaskContext
			);
			TaskLoop.getInstance().getSyncTaskScheduleDefinitionQueue().offer(resultContext);
		}
	}

}
