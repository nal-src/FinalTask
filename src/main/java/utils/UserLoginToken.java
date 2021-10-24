package utils;

import api.AuthApi;
import entity.User;
import lombok.SneakyThrows;
import okhttp3.Response;
import org.json.JSONObject;

public class UserLoginToken {
    @SneakyThrows
    public static String get(User user) {
        Response response = AuthApi.signIn(user);
        if(response.isSuccessful()) {
            return tokenFromResponse(response);
        }
        registerUser(user);

        response = AuthApi.signIn(user);
        if(response.isSuccessful()) {
            return tokenFromResponse(response);
        }
        throw new Exception("Fail login user");
    }

    @SneakyThrows
    private static String tokenFromResponse(Response response) {
        JSONObject responseObject = new JSONObject(response.body().string());
        return responseObject.get("token").toString();
    }

    @SneakyThrows
    private static void registerUser(User user) {
        Response response = AuthApi.signUp(user);
        if(!response.isSuccessful()) {
            throw new Exception("Fail register user");
        }
    }
}
