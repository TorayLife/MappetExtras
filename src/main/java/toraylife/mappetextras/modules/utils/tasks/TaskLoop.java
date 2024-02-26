package toraylife.mappetextras.modules.utils.tasks;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Function;

public class TaskLoop {
	private static final TaskLoop instance = new TaskLoop();

	private ScheduledExecutorService executorService;
	private final ConcurrentLinkedQueue<SyncTaskScheduleDef> asyncTaskQueue = new ConcurrentLinkedQueue<>();


	private TaskLoop() {}


	public static TaskLoop getInstance() {
		return TaskLoop.instance;
	}


	public <TResult> Task<Void, TResult> first(Function<TaskContext<Void>, TaskResult<TResult>> taskExecutable) {
		SyncTask<Void, TResult> task = new SyncTask<>(null, TaskDelayTime.ZERO_TICKS, taskExecutable);
		task.setInitTask(task);
		return task;
	}

	public <TResult> Task<Void, TResult> firstAsync(Function<TaskContext<Void>, TaskResult<TResult>> taskExecutable) {
		AsyncTask<Void, TResult> task = new AsyncTask<>(null, TaskDelayTime.ZERO_MILLIS, taskExecutable);
		task.setInitTask(task);
		return task;
	}


	public LoopTaskBuilder<Void, Void> firstLoop(int iterationCount) {
		return new LoopTaskBuilder<>(null, iterationCount, TaskDelayTime.ZERO_TICKS);
	}


	public DelayedTaskBuilder<Void> firstWaitTicks(int ticks) {
		return new DelayedTaskBuilder<>(null, new TaskDelayTime(ticks, TaskDelayTime.Unit.TICKS));
	}

	public DelayedTaskBuilder<Void> firstWaitMillis(int millis) {
		return new DelayedTaskBuilder<>(null, new TaskDelayTime(millis, TaskDelayTime.Unit.MILLIS));
	}


	public void start(int threads) {
		this.executorService = Executors.newScheduledThreadPool(threads);
	}

	public ScheduledExecutorService getExecutorService() {
		return executorService;
	}

	public Queue<SyncTaskScheduleDef> getSyncTaskScheduleQueue() {
		return asyncTaskQueue;
	}

}
