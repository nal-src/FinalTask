package api;

import entity.Job;
import entity.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.UserData;
import utils.UserLoginToken;

import java.util.List;

public class JobTest extends BaseApiTest {
    @Test
    public void createJobTest() {
        User userData = UserData.get();
        String token = UserLoginToken.get(userData);
        Job newJob = new Job();
        newJob.setTitle("My Job");
        newJob.setDescription("My Job Description");
        newJob.setPrice(999);

        Job createdJob = JobApi.create(token, newJob);
        Assert.assertEquals(createdJob.getTitle(), newJob.getTitle());
        Assert.assertEquals(createdJob.getDescription(), newJob.getDescription());
        Assert.assertEquals(createdJob.getPrice(), newJob.getPrice());
    }

    @Test
    public void deleteJobTest() {
        User userData = UserData.get();
        String token = UserLoginToken.get(userData);
        Job newJob = new Job();
        newJob.setTitle("My Job");
        newJob.setDescription("My Job Description");
        newJob.setPrice(999);

        Job createdJob = JobApi.create(token, newJob);
        String message = JobApi.delete(token, createdJob.getId());

        Assert.assertEquals(message, "Job is deleted");
    }

    @Test
    public void getAllJobTest() {
        User userData = UserData.get();
        String token = UserLoginToken.get(userData);
        Job newJob = new Job();
        newJob.setTitle("My Job");
        newJob.setDescription("My Job Description");
        newJob.setPrice(999);
        JobApi.create(token, newJob);

        List<Job> allJobs = JobApi.getAll(token);

        Assert.assertTrue(allJobs.size() >= 1);
    }

    @Test
    public void getAllJobByUserTest() {
        User userData = UserData.get();
        String token = UserLoginToken.get(userData);
        Job newJob = new Job();
        newJob.setTitle("My Job");
        newJob.setDescription("My Job Description");
        newJob.setPrice(999);
        JobApi.create(token, newJob);

        List<Job> allJobs = JobApi.getAllByUser(token);

        Assert.assertEquals(allJobs.size(), 1);
    }

    @Test
    public void getJobByIdTest() {
        User userData = UserData.get();
        String token = UserLoginToken.get(userData);
        Job newJob = new Job();
        newJob.setTitle("My Job");
        newJob.setDescription("My Job Description");
        newJob.setPrice(999);
        Job createdJob = JobApi.create(token, newJob);

        Job job = JobApi.getById(token, createdJob.getId());

        Assert.assertEquals(job.getId(), createdJob.getId());
        Assert.assertEquals(job.getTitle(), createdJob.getTitle());
        Assert.assertEquals(job.getDescription(), createdJob.getDescription());
        Assert.assertEquals(job.getPrice(), createdJob.getPrice());
    }
}
