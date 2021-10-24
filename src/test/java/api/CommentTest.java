package api;

import com.github.javafaker.Faker;
import entity.Comment;
import entity.Job;
import entity.User;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.UserData;
import utils.UserLoginToken;

import java.util.List;

public class CommentTest extends BaseApiTest {
    @SneakyThrows
    @Test
    public void createCommentTest() {
        User userData = UserData.get();
        String token = UserLoginToken.get(userData);

        Faker faker = new Faker();
        Job job = new Job();
        job.setTitle(faker.job().title());
        job.setDescription(faker.lorem().paragraph());
        job.setPrice(faker.number().numberBetween(100, 900));
        Job createdJob = JobApi.create(token, job);

        Comment newComment = new Comment();
        newComment.setMessage(faker.lorem().sentence());
        Comment createdComment = CommentApi.create(token, createdJob.getId(), newComment);
        Assert.assertEquals(createdComment.getMessage(), newComment.getMessage());
        Assert.assertNotNull(createdComment.getCommentDate());
        Assert.assertNotNull(createdComment.getUsername());
    }

    @SneakyThrows
    @Test
    public void getAllCommentsForJobTest() {
        User userData = UserData.get();
        String token = UserLoginToken.get(userData);

        Faker faker = new Faker();
        Job job = new Job();
        job.setTitle(faker.job().title());
        job.setDescription(faker.lorem().paragraph());
        job.setPrice(faker.number().numberBetween(100, 900));
        Job createdJob = JobApi.create(token, job);

        Comment newComment = new Comment();
        newComment.setMessage(faker.lorem().sentence());
        CommentApi.create(token, createdJob.getId(), newComment);
        CommentApi.create(token, createdJob.getId(), newComment);

        List<Comment> comments = CommentApi.getAllForJob(token, createdJob.getId());
        Assert.assertEquals(comments.size(), 2);
    }
}
