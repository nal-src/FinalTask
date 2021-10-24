package ui;

import api.JobApi;
import com.github.javafaker.Faker;
import entity.Job;
import entity.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.JobPage;
import utils.UserData;
import utils.UserLoginToken;

public class UI_3_Test extends BaseTest {
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
        JobApi.create(token, job);
    }

    @Test(dependsOnGroups = {"auth"})
    public void showJobAndCreateCommentTest() {
        openHomePage();
        JobPage jobPage = new HomePage().goToLoginPage()
                .setUserName(user.getUsername())
                .setPassword(user.getPassword())
                .clickLoginButton()
                .viewJobDetails(job);

        Assert.assertEquals(jobPage.getCountComments(), 0);

        Faker faker = new Faker();
        String commentText = faker.lorem().sentence();
        jobPage.addComment(commentText);

        Assert.assertEquals(jobPage.jobCard.getTitle(), job.getTitle());
        Assert.assertEquals(jobPage.jobCard.getDescription(), job.getDescription());
        Assert.assertEquals(jobPage.jobCard.getPrice(), job.getPrice());
        Assert.assertEquals(jobPage.getCountComments(), 1);
    }
}
