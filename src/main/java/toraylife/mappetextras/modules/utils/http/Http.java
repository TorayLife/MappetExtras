package toraylife.mappetextras.modules.utils.http;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;

public class Http {
	public static InputStream post(String url, JsonObject body, int timeout) throws IOException {
		Gson gson = new Gson();
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(body.toString(), ContentType.APPLICATION_JSON));

		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(timeout)
				.setConnectTimeout(timeout)
				.setSocketTimeout(timeout)
				.build();

		post.setConfig(requestConfig);

		CloseableHttpResponse httpResponse = client.execute(post);
		return httpResponse.getEntity().getContent();
	}

	public static InputStream get(String url, int timeout) throws IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);

		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(timeout)
				.setConnectTimeout(timeout)
				.setSocketTimeout(timeout)
				.build();

		get.setConfig(requestConfig);

		CloseableHttpResponse httpResponse = client.execute(get);
		return httpResponse.getEntity().getContent();
	}
}
