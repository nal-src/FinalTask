package api;

import com.google.gson.Gson;
import entity.User;
import lombok.SneakyThrows;
import okhttp3.Response;

public class UserApi extends BaseApi {
    @SneakyThrows
    public static User getUserInfo(String token) {
        request = requestJsonBuilder(token)
                .url(fullUrl("/api/user/"))
                .build();

        Response response = call(request);
        if(response.isSuccessful()) {
            return new Gson().fromJson(response.body().string(), User.class);
        }
        throw new Exception("Failed get user info by token");
    }

    @SneakyThrows
    public static User getUserById(String token, int userId) {
        request = requestJsonBuilder(token)
                .url(fullUrl("/api/user/" + userId))
                .build();

        Response response = call(request);
        if(response.isSuccessful()) {
            return new Gson().fromJson(response.body().string(), User.class);
        }
        throw new Exception("Failed get user info by id");
    }

    @SneakyThrows
    public static User update(String token, User user) {
        String jsonString = new Gson().toJson(user);

        request = requestJsonBuilder(token)
                .url(fullUrl("/api/user/update"))
                .post(requestBody(jsonString))
                .build();

        Response response = call(request);
        if(response.isSuccessful()) {
            return new Gson().fromJson(response.body().string(), User.class);
        }
        throw new Exception("Failed update user");

    }
}
