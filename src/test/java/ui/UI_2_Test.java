package ui;

import api.AuthApi;
import com.github.javafaker.Faker;
import entity.Job;
import entity.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MainPage;
import pages.ProfilePage;
import utils.UserData;

public class UI_2_Test extends BaseTest {
    private User user;

    @BeforeClass
    public void prepareTestData() {
        user = UserData.get();
        AuthApi.signUp(user);
    }

    @Test(dependsOnGroups = {"auth"})
    public void createNewJobTest() {
        openHomePage();
        MainPage mainPage = new HomePage().goToLoginPage()
                .setUserName(user.getUsername())
                .setPassword(user.getPassword())
                .clickLoginButton();
        ProfilePage profilePage = mainPage.header.goToProfile();

        Faker faker = new Faker();
        Job job = new Job();
        job.setTitle(faker.job().title());
        job.setDescription(faker.lorem().paragraph());
        job.setPrice(faker.number().numberBetween(1000, 9000));

        profilePage.addJob()
                .setTitle(job.getTitle())
                .setDescription(job.getDescription())
                .setPrice(job.getPrice())
                .create();

        Assert.assertTrue(profilePage.isPresentJob(job));

    }
}
