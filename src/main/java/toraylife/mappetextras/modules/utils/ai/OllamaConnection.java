package toraylife.mappetextras.modules.utils.ai;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import mchorse.mappet.api.utils.AbstractData;
import mchorse.mclib.config.values.GenericValue;
import mchorse.mclib.config.values.ValueBoolean;
import mchorse.mclib.config.values.ValueInt;
import mchorse.mclib.config.values.ValueString;
import mchorse.mclib.utils.ValueSerializer;
import net.minecraft.nbt.NBTTagCompound;
import toraylife.mappetextras.modules.utils.http.Http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class OllamaConnection extends AbstractData {
	public ValueString url = new ValueString("url");
	public ValueString model = new ValueString("model");
	public ValueString format = new ValueString("format");
	public ValueString systemPrompt = new ValueString("system");
	public ValueString template = new ValueString("template");
	public ValueBoolean stream = new ValueBoolean("stream");
	public ValueInt timeout = new ValueInt("timeout");

	public ValueSerializer serializer;

	public OllamaConnection() {
		this.serializer = new ValueSerializer();
		this.url.set("http://localhost:11434");
		this.format.set("json");
		this.systemPrompt.set("");
		this.template.set("");
		this.stream.set(true);
		this.timeout.set(60);
		this.registerValue(this.url);
		this.registerValue(this.model);
		this.registerValue(this.format);
		this.registerValue(this.systemPrompt);
		this.registerValue(this.template);
		this.registerValue(this.stream);
		this.registerValue(this.timeout);
	}

	public void registerValue(GenericValue value) {
		this.serializer.registerValue(value.id, value.id, value, true, true);
	}

	public OllamaResponse getResponse(String prompt, ArrayList<Integer> context) {
		OllamaResponse response = new OllamaResponse();
		Gson gson = new Gson();
		JsonObject json = this.serializer.toJSON().getAsJsonObject();

		json.addProperty("prompt", prompt);
		if (context != null) {
			json.add("context", gson.toJsonTree(context));
		}
		// Because of value API bug with alwaysWrite option.
		json.addProperty("stream", this.stream.get());
		if (this.systemPrompt.get().isEmpty()) {
			json.remove("system");
		}
		if (this.template.get().isEmpty()) {
			json.remove("template");
		}

		ArrayList<String> responses = new ArrayList<>();

		try (InputStream responseBodyStream = Http.post(this.url.get() + "/api/generate", json, this.timeout.get() * 1000)) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(responseBodyStream, StandardCharsets.UTF_8));
			String line;
			while ((line = reader.readLine()) != null) {
				response = gson.fromJson(line, OllamaResponse.class);
				responses.add(response.toString());
				if (!response.error.isEmpty()) {
					break;
				}
			}

			if (this.stream.get()) {
				response.response = responses.stream().collect(Collectors.joining());
			}
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			response.error = e.getMessage();
			return response;
		}
	}

	public OllamaChatResponse getChatResponse(String prompt, ArrayList<Message> messages) {
		OllamaChatResponse response = new OllamaChatResponse();
		Gson gson = new Gson();
		JsonObject json = this.serializer.toJSON().getAsJsonObject();

		if (messages != null) {
			messages.add(new Message("user", prompt));
			json.add("messages", gson.toJsonTree(messages));
		}
		// Because of value API bug with alwaysWrite option.
		json.addProperty("stream", this.stream.get());
		if (this.systemPrompt.get().isEmpty()) {
			json.remove("system");
		} else if (messages != null && messages.size() > 0 && !messages.get(0).role.equals("system")) {
			messages.add(0, new Message("system", this.systemPrompt.get()));
		}
		if (this.template.get().isEmpty()) {
			json.remove("template");
		}

		ArrayList<String> responses = new ArrayList<>();

		try (InputStream responseBodyStream = Http.post(this.url.get() + "/api/chat", json, this.timeout.get() * 1000)) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(responseBodyStream, StandardCharsets.UTF_8));
			String line;
			while ((line = reader.readLine()) != null) {
				response = gson.fromJson(line, OllamaChatResponse.class);
				responses.add(response.toString());
				if (!response.error.isEmpty()) {
					break;
				}
			}

			if (this.stream.get()) {
				response.message.content = String.join("", responses);
			}
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			response.error = e.getMessage();
			return response;
		}
	}

	public OllamaModelListResponse getModelList() {
		OllamaModelListResponse response = new OllamaModelListResponse();
		Gson gson = new Gson();

		try (InputStream responseBodyStream = Http.get(this.url.get() + "/api/tags", 20000)) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(responseBodyStream, StandardCharsets.UTF_8));
			String line;
			while ((line = reader.readLine()) != null) {
				response = gson.fromJson(line, OllamaModelListResponse.class);
			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.error = e.getMessage();
			return response;
		}
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound tag = this.serializer.toNBT();
		tag.setString("url", this.url.get());
		tag.setString("model", this.model.get());
		tag.setString("format", this.format.get());
		tag.setString("system", this.systemPrompt.get());
		tag.setString("template", this.template.get());
		tag.setBoolean("stream", this.stream.get());
		tag.setInteger("timeout", this.timeout.get());
		return tag;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		this.serializer.fromNBT(nbt);
	}
}

