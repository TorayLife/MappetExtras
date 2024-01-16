package toraylife.mappetextras.modules.utils.ai;

import mchorse.mclib.client.gui.utils.keys.IKey;

public class OllamaChatResponse implements IResponse {
	public String model = "";
	public String error = "";
	public long eval_duration = 0L;

	public Message message;

	@Override
	public String toString() {
		return error.isEmpty() ? message.content : IKey.format("mappetextras.error", error).toString();
	}

	@Override
	public Long getEvalDuration() {
		return eval_duration;
	}

	boolean done;
}
