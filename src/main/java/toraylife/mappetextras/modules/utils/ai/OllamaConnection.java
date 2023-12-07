package toraylife.mappetextras.modules.utils.ai;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import mchorse.mappet.api.utils.AbstractData;
import mchorse.mclib.config.values.GenericValue;
import mchorse.mclib.config.values.ValueBoolean;
import mchorse.mclib.config.values.ValueString;
import mchorse.mclib.utils.ValueSerializer;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class OllamaConnection extends AbstractData {
	public ValueString url = new ValueString("url");
	public ValueString model = new ValueString("model");
	public ValueString format = new ValueString("format");
	public ValueString systemPrompt = new ValueString("system");
	public ValueString template = new ValueString("template");
	public ValueBoolean stream = new ValueBoolean("stream");

	public ValueSerializer serializer;

	public OllamaConnection() {
		this.serializer = new ValueSerializer();
		this.url.set("http://localhost:11434");
		this.format.set("json");
		this.systemPrompt.set("");
		this.template.set("");
		this.stream.set(true);
		this.registerValue(this.url);
		this.registerValue(this.model);
		this.registerValue(this.format);
		this.registerValue(this.systemPrompt);
		this.registerValue(this.template);
		this.registerValue(this.stream);
	}

	public void registerValue(GenericValue value) {
		this.serializer.registerJSONValue(value.id, value, true);
	}

	public String getResponse(String prompt) {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpPost post = new HttpPost(this.url + "/api/generate");
			JsonObject json = this.serializer.toJSON().getAsJsonObject();

			json.addProperty("prompt", prompt);
			// Because of value API bug with alwaysWrite option.
			json.addProperty("stream", true);
			if (this.systemPrompt.get().isEmpty()) {
				json.remove("system");
			}
			if (this.template.get().isEmpty()) {
				json.remove("template");
			}


			post.setEntity(new StringEntity(json.toString(), ContentType.APPLICATION_JSON));
			CloseableHttpResponse httpResponse = client.execute(post);
			String data = "";
			InputStream responseBodyStream = httpResponse.getEntity().getContent();
			ArrayList<OllamaResponse> responses = new ArrayList<>();
			Gson gson = new Gson();
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(responseBodyStream, StandardCharsets.UTF_8))) {
				String line;
				while ((line = reader.readLine()) != null) {
					OllamaResponse response = gson.fromJson(line, OllamaResponse.class);
					responses.add(response);
				}
			}
			StringBuilder responseString = new StringBuilder();
			responses.forEach(response -> responseString.append(response.response));

			return responseString.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
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
		return tag;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		this.serializer.fromNBT(nbt);
	}
}
