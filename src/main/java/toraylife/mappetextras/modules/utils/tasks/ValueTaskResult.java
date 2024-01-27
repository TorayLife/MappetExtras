package toraylife.mappetextras.modules.utils.tasks;

public class ValueTaskResult<TValue> implements TaskResult<TValue> {
	private final TValue value;

	public ValueTaskResult(TValue value) {
		this.value = value;
	}

	public TValue getValue() {
		return value;
	}
}
