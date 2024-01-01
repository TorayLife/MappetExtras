package toraylife.mappetextras.modules.utils.tasks;

public class TaskDelayTime {
	private final int delay;
	private final Unit unit;


	public TaskDelayTime(int delay, Unit unit) {
		this.delay = delay;
		this.unit = unit;
	}


	public int getDelay() {
		return delay;
	}

	public Unit getUnit() {
		return unit;
	}

	public TaskDelayTime toTicks() {
		if (unit == Unit.MILLIS) {
			return new TaskDelayTime(delay / 50, Unit.TICKS);
		}

		return this;
	}

	public TaskDelayTime toMillis() {
		if (unit == Unit.TICKS) {
			return new TaskDelayTime(delay * 50, Unit.MILLIS);
		}

		return this;
	}


	public enum Unit {
		TICKS, MILLIS
	}

}
