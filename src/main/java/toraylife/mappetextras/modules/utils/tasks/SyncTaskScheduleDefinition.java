package toraylife.mappetextras.modules.utils.tasks;

@SuppressWarnings("rawtypes")
public class SyncTaskScheduleDefinition {
	private final Task task;
	private final TaskContext context;


	public SyncTaskScheduleDefinition(Task<?, ?> task, TaskContext<?> context) {
		this.task = task;
		this.context = context;
	}

	@SuppressWarnings("unchecked")
	public void schedule() {
		this.task.schedule(this.context);
	}

}
