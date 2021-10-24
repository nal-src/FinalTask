package ui;

import entity.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.UserData;

public class RegisterTest extends BaseTest {
    private User user;

    @BeforeClass
    public void beforeClass() {
        user = UserData.get();
    }

    @Test(groups = {"auth"})
    public void registerTest() {
        openHomePage();
        RegisterPage registerPage = new HomePage().goToRegisterPage();
        LoginPage loginPage = registerPage
                .setUserName(user.getUsername())
                .setName(user.getName())
                .setLastName(user.getLastname())
                .setPassword(user.getPassword())
                .confirmPassword(user.getPassword())
                .clickRegisterButton();


        String titlePage = loginPage.getTitle();
        Assert.assertEquals(titlePage, "Login");
    }
}
