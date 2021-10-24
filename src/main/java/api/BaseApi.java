package api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Call;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class BaseApi {
    protected static Request request;
    protected static final OkHttpClient client = new OkHttpClient();

    protected static String fullUrl(String url) {
        return "https://freelance.lsrv.in.ua" + url;
    }

    protected static Request.Builder requestJsonBuilder() {
        return new Request.Builder().header("Content-Type", "application/json");
    }

    protected static Request.Builder requestJsonBuilder(String token) {
        return new Request.Builder()
                .header("Content-Type", "application/json")
                .header("Authorization", token);
    }

    protected static Request.Builder requestMultipartFormBuilder(String token) {
        return new Request.Builder()
                .header("Content-Type", "multipart/form-data")
                .header("Authorization", token);
    }

    protected static RequestBody requestBody(String jsonString) {
        return RequestBody.create(jsonString.getBytes(StandardCharsets.UTF_8));
    }

    protected static Response call(Request request) throws IOException {
        Call call = client.newCall(request);
        return call.execute();
    }

    protected static JSONObject jsonResponse(Response response) throws IOException {
        return new JSONObject(response.body().string());
    }
}