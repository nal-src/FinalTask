package ui;

import api.CommentApi;
import api.JobApi;
import com.github.javafaker.Faker;
import entity.Comment;
import entity.Job;
import entity.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MainPage;
import pages.ProfilePage;
import pages.components.JobCard;
import utils.UserData;
import utils.UserLoginToken;

public class UI_4_Test extends BaseTest {
    private User user;
    private Job job;

    @BeforeClass
    public void prepareTestData() {
        user = UserData.get();
        String token = UserLoginToken.get(user);
        Faker faker = new Faker();
        job = new Job();
        job.setTitle(faker.job().title());
        job.setDescription(faker.lorem().paragraph());
        job.setPrice(faker.number().numberBetween(1000, 9000));
        job = JobApi.create(token, job);
        Comment comment = new Comment();
        comment.setMessage(faker.lorem().sentence());
        CommentApi.create(token, job.getId(), comment);

    }

    @Test(dependsOnGroups = {"auth"})
    public void createNewJobTest() {
        openHomePage();
        MainPage mainPage = new HomePage().goToLoginPage()
                .setUserName(user.getUsername())
                .setPassword(user.getPassword())
                .clickLoginButton();
        ProfilePage profilePage = mainPage.header.goToProfile();

        JobCard findJob = profilePage.getJob(job);
        Assert.assertEquals(findJob.getCountComments(), 1);
        findJob.clickRemoveJobButton();
        Assert.assertFalse(profilePage.isPresentJob(job));
    }
}
