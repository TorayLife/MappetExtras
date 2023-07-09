package toraylife.mappetextras.modules.utils;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Beautifier {
    public static String beautify(String jsCode) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://www.10bestdesign.com/dirtymarkup/api/js");

        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("code", jsCode));
        params.add(new BasicNameValuePair("indent", "4"));
        params.add(new BasicNameValuePair("line-length", "80"));
        params.add(new BasicNameValuePair("brace-style", "collapse"));
        params.add(new BasicNameValuePair("spaces-in-parenthesis", "false"));
        params.add(new BasicNameValuePair("break-chained-methods", "false"));
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        //Execute and get the response.
        HttpResponse response = httpclient.execute(httpPost);
        HttpEntity entity = response.getEntity();

        String responseString = "";

        if (entity != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
            responseString = reader.lines().collect(Collectors.joining());
        }

        return Beautifier.processResponse(responseString);
    }

    private static String processResponse(String responseString) {
        if (responseString.equals("")) {
            return "";
        }

        Gson gson = new Gson();
        Response response = gson.fromJson(responseString, Response.class);

        return response.clean;
    }
}

class Response {
    public String clean;
}
