package api;

import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

public class BaseApiTest {
    protected JSONObject jsonResponse(Response response) throws IOException {
        return new JSONObject(response.body().string());
    }
}
