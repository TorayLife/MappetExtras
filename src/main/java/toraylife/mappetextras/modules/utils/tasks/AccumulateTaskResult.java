package toraylife.mappetextras.modules.utils.tasks;

public class AccumulateTaskResult<TAccumulator> implements TaskResult<TAccumulator> {
	private final TAccumulator accumulator;

	public AccumulateTaskResult(TAccumulator accumulator) {
		this.accumulator = accumulator;
	}

	public TAccumulator getAccumulator() {
		return accumulator;
	}

}
