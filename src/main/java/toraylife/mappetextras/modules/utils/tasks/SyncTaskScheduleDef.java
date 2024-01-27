package toraylife.mappetextras.modules.utils.tasks;

@SuppressWarnings("rawtypes")
public class SyncTaskScheduleDef {
	private final Task task;
	private final TaskContext context;


	public SyncTaskScheduleDef(Task<?, ?> task, TaskContext<?> context) {
		this.task = task;
		this.context = context;
	}

	@SuppressWarnings("unchecked")
	public void schedule() {
		this.task.schedule(this.context);
	}

}
