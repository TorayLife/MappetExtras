package toraylife.mappetextras.modules.utils.tasks;

import mchorse.mappet.api.scripts.user.IScriptEvent;

public class TaskContext<T> {
	private final IScriptEvent scriptContext;
	private final T result;


	public TaskContext(IScriptEvent context, T result) {
		this.scriptContext = context;
		this.result = result;
	}


	public T getResult() {
		return result;
	}

	public IScriptEvent getScriptContext() {
		return scriptContext;
	}

}
