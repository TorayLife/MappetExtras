package toraylife.mappetextras.modules.utils.tasks;

public class DelegateTaskResult<TDelegateResult> implements TaskResult<TDelegateResult> {
	private final Task<?, TDelegateResult> delegateTask;


	public DelegateTaskResult(Task<?, TDelegateResult> delegateTask) {
		this.delegateTask = delegateTask;
	}


	public Task<?, TDelegateResult> getDelegateTask() {
		return delegateTask;
	}
}
