package toraylife.mappetextras.modules.utils.tasks;

import jdk.nashorn.internal.runtime.Undefined;

public interface TaskResult<TResult> {

	@SuppressWarnings("unchecked")
	static <TResult> TaskResult<TResult> from(Object objectResult) {
		if (objectResult instanceof TaskResult<?>) {
			return (TaskResult<TResult>) objectResult;
		}

		if (objectResult == null || objectResult instanceof Undefined) {
			return (TaskResult<TResult>) new EmptyTaskResult();
		}

		return new ValueTaskResult<>((TResult) objectResult);
	}

}
