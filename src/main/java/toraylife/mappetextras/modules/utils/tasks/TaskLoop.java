package toraylife.mappetextras.modules.utils.tasks;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Function;

public class TaskLoop {
	private static final TaskLoop instance = new TaskLoop();

	private ScheduledExecutorService executorService;
	private final ConcurrentLinkedQueue<ContextualizedSyncTaskDefinition> asyncTaskQueue = new ConcurrentLinkedQueue<>();


	private TaskLoop() {}


	public static TaskLoop getInstance() {
		return TaskLoop.instance;
	}


	public static <TResult> Task<Void, TResult> begin(Function<TaskContext<Void>, TResult> taskExecutable) {
		SyncTask<Void, TResult> task = new SyncTask<>(taskExecutable);
		task.setInitTask(task);
		return task;
	}

	public static <TResult> Task<Void, TResult> beginAsync(Function<TaskContext<Void>, TResult> taskExecutable) {
		AsyncTask<Void, TResult> task = new AsyncTask<>(taskExecutable);
		task.setInitTask(task);
		return task;
	}


	public void start(int threadsCount) {
		this.executorService = Executors.newScheduledThreadPool(threadsCount);
	}

	public ScheduledExecutorService getExecutorService() {
		return executorService;
	}

	public ConcurrentLinkedQueue<ContextualizedSyncTaskDefinition> getAsyncTaskQueue() {
		return asyncTaskQueue;
	}

}
