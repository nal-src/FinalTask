package api;

import com.github.javafaker.Faker;
import entity.User;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.UserData;
import utils.UserLoginToken;

public class UserTest extends BaseApiTest {
    @SneakyThrows
    @Test
    public void getUserInfoTest() {
        User userData = UserData.get();
        String token = UserLoginToken.get(userData);
        User user = UserApi.getUserInfo(token);

        Assert.assertEquals(user.getUsername(), userData.getUsername());
    }

    @SneakyThrows
    @Test
    public void getUserByIdTest() {
        String verifiedUserToken = UserLoginToken.get(UserData.get());
        User verifiedUser = UserApi.getUserInfo(verifiedUserToken);

        String currentUserToken = UserLoginToken.get(UserData.get());;
        User user = UserApi.getUserById(currentUserToken, verifiedUser.getId());

        Assert.assertEquals(user.getUsername(), verifiedUser.getUsername());
    }

    @SneakyThrows
    @Test
    public void updateUserTest() {
        User user = UserData.get();
        String token = UserLoginToken.get(user);
        Faker faker = new Faker();
        user.setName(faker.name().firstName());
        user.setLastname(faker.name().lastName());

        User updateUser = UserApi.update(token, user);
        Assert.assertEquals(updateUser.getName(), user.getName());
        Assert.assertEquals(updateUser.getLastname(), user.getLastname());
    }
}
