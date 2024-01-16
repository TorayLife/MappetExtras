package toraylife.mappetextras.modules.utils.ai;

import mchorse.mclib.client.gui.utils.keys.IKey;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class OllamaModelListResponse {
	public ArrayList<OllamaModel> models = new ArrayList<>();

	public String error = "";

	public String toString() {
		return error.isEmpty() ? models.stream().map(m -> m.name).collect(Collectors.joining(", \n")) : IKey.format("mappetextras.error", error).toString();
	}
}
