package api;

import entity.User;
import lombok.SneakyThrows;
import okhttp3.Response;
import org.json.JSONObject;

public class AuthApi extends BaseApi {
    public static String testUsername = "nal-hillel";
    public static String testPassword = "12345678";

    @SneakyThrows
    public static Response signUp(User user) {
        JSONObject jsonObject =  new JSONObject();
        jsonObject.put("username", user.getUsername());
        jsonObject.put("password", user.getPassword());
        jsonObject.put("confirmPassword", user.getPassword());

        request = requestJsonBuilder()
                .url(fullUrl("/api/auth/signup"))
                .post(requestBody(jsonObject.toString()))
                .build();

        return call(request);
    }

    @SneakyThrows
    public static Response signIn(User user) {
        JSONObject jsonObject =  new JSONObject();
        jsonObject.put("username", user.getUsername());
        jsonObject.put("password", user.getPassword());

        request = requestJsonBuilder()
                .url(fullUrl("/api/auth/signin"))
                .post(requestBody(jsonObject.toString()))
                .build();

        return call(request);
    }
}
