package api;

import entity.User;
import lombok.SneakyThrows;
import okhttp3.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.UserData;

public class SignUpTest extends BaseApiTest {
    @SneakyThrows
    @Test
    public void validDataTest() {
        User user = UserData.get();
        Response response = AuthApi.signUp(user);

        Assert.assertEquals(response.code(), 200);

        JSONObject responseObject = jsonResponse(response);
        Assert.assertEquals(responseObject.get("message"), "User registered successfully");
    }

    @SneakyThrows
    @Test
    public void shortPasswordTest() {
        User user = UserData.get();
        user.setPassword("123");
        Response response = AuthApi.signUp(user);

        Assert.assertEquals(response.code(), 400);

        JSONObject responseObject = jsonResponse(response);
        Assert.assertEquals(responseObject.get("password"), "size must be between 8 and 2147483647");
    }
}
