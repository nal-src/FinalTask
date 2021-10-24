package api;

import entity.Image;
import entity.User;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.UserData;
import utils.UserLoginToken;

import java.io.File;

public class ImageTest extends BaseApiTest {
    @SneakyThrows
    @Test
    public void uploadImageTest() {
        User userData = UserData.get();
        String token = UserLoginToken.get(userData);

        File image = new File(this.getClass().getClassLoader().getResource("test_image.jpeg").toURI());
        String message = ImageApi.upload(token, image);

        Assert.assertEquals(message, "User image was uploaded");
    }

    @SneakyThrows
    @Test
    public void getUserImageTest() {
        User userData = UserData.get();
        String token = UserLoginToken.get(userData);

        File image = new File(this.getClass().getClassLoader().getResource("test_image.jpeg").toURI());
        ImageApi.upload(token, image);

        Image findImage = ImageApi.getImageByUser(token);

        Assert.assertEquals(findImage.getName(), image.getName());
    }
}
