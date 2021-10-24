package ui;

import api.AuthApi;
import entity.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MainPage;
import pages.ProfilePage;
import utils.UserData;

public class UI_1_Test extends BaseTest {
    private User user;

    @BeforeClass
    public void prepareTestData() {
        user = UserData.get();
        AuthApi.signUp(user);
    }

    @Test(dependsOnGroups = {"auth"})
    public void updateProfileTest() {
        openHomePage();
        MainPage mainPage = new HomePage().goToLoginPage()
                .setUserName(user.getUsername())
                .setPassword(user.getPassword())
                .clickLoginButton();
        ProfilePage profilePage = mainPage.header.goToProfile();

        User newUserData = UserData.get();

        profilePage.editInfo()
                .setName(newUserData.getName())
                .setLastName(newUserData.getLastname())
                .update();

        Assert.assertEquals(profilePage.getNameInfo(),
                String.format("%s %s", newUserData.getName(), newUserData.getLastname()));
    }
}
