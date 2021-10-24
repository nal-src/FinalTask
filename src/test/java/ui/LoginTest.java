package ui;

import api.AuthApi;
import com.codeborne.selenide.WebDriverRunner;
import entity.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MainPage;
import utils.UserData;

public class LoginTest extends BaseTest {
    private User user;

    @BeforeClass
    public void prepareTestData() {
        user = UserData.get();
        AuthApi.signUp(user);
    }

    @Test(groups = {"auth"})
    public void loginTest() {
        openHomePage();
        MainPage mainPage = new HomePage().goToLoginPage()
                .setUserName(user.getUsername())
                .setPassword(user.getPassword())
                .clickLoginButton();

        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        String[] urlParts = currentUrl.split("/");

        Assert.assertEquals(urlParts[urlParts.length -1], mainPage.relativeUrlPath);
    }
}
