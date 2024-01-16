package toraylife.mappetextras.modules.utils.ai;

public enum OllamaConnectionType {
	TEXT(""),
	JSON("json");

	public String value;

	OllamaConnectionType(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value;
	}

}
