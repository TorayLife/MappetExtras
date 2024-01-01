package toraylife.mappetextras.modules.utils.tasks;

public class ContextualizedSyncTaskDefinition {
	private final SyncTask<Object, ?> task;
	private final TaskContext<Object> context;


	@SuppressWarnings("unchecked")
	public ContextualizedSyncTaskDefinition(SyncTask<?, ?> task, TaskContext<?> context) {
		this.task = (SyncTask<Object, ?>) task;
		this.context = (TaskContext<Object>) context;
	}

	public void run() {
		this.task.run(this.context);
	}

}
