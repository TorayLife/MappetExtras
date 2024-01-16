package toraylife.mappetextras.modules.utils.ai;

import mchorse.mclib.client.gui.utils.keys.IKey;

import java.util.ArrayList;

public class OllamaResponse implements IResponse {
	public String model = "";
	public String response = "";
	public String error = "";
	public long eval_duration = 0L;

	public ArrayList<Integer> context = new ArrayList<>();

	@Override
	public String toString() {
		return error.isEmpty() ? response : IKey.format("mappetextras.error", error).toString();
	}

	@Override
	public Long getEvalDuration() {
		return eval_duration;
	}

	boolean done;
}
