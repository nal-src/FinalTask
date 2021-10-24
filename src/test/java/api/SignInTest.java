package api;

import entity.User;
import lombok.SneakyThrows;
import okhttp3.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.UserData;

public class SignInTest extends BaseApiTest {
    @SneakyThrows
    @Test
    public void validDataTest() {
        User user = UserData.get();
        registerUser(user);
        Response response = AuthApi.signIn(user);
        Assert.assertEquals(response.code(), 200);

        JSONObject responseObject = jsonResponse(response);

        Assert.assertEquals(responseObject.get("success"), true);
        Assert.assertFalse(responseObject.get("token").toString().isEmpty());

    }

    @SneakyThrows
    @Test
    public void invalidDataTest() {
        User user = new User();
        user.setUsername("1");
        user.setPassword("1");
        Response response = AuthApi.signIn(user);
        Assert.assertEquals(response.code(), 401);

        JSONObject responseObject = jsonResponse(response);

        Assert.assertEquals(responseObject.get("username"), "Invalid username");
        Assert.assertEquals(responseObject.get("password"), "Invalid password");
    }

    @SneakyThrows
    private void registerUser(User user) {
        Response response = AuthApi.signUp(user);
        if(!response.isSuccessful()) {
            throw new Exception("Fail register user");
        }
    }
}
