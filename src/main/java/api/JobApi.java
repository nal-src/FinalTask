package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Job;
import lombok.SneakyThrows;
import okhttp3.Response;

import java.util.List;

public class JobApi extends BaseApi {
    @SneakyThrows
    public static List<Job> getAll(String token) {
        request = requestJsonBuilder(token)
                .url(fullUrl("/api/job/all"))
                .build();

        Response response = call(request);
        if(response.isSuccessful()) {
            return new Gson().fromJson(response.body().string(), new TypeToken<List<Job>>(){}.getType());
        }
        throw new Exception("Failed get list of all jobs");
    }

    @SneakyThrows
    public static List<Job> getAllByUser(String token) {
        request = requestJsonBuilder(token)
                .url(fullUrl("/api/job/user/jobs"))
                .build();

        Response response = call(request);
        if(response.isSuccessful()) {
            return new Gson().fromJson(response.body().string(), new TypeToken<List<Job>>(){}.getType());
        }
        throw new Exception("Failed get list of all jobs for user");
    }

    @SneakyThrows
    public static Job getById(String token, int id) {
        request = requestJsonBuilder(token)
                .url(fullUrl("/api/job/" + id))
                .build();

        Response response = call(request);
        if(response.isSuccessful()) {
            return new Gson().fromJson(response.body().string(), Job.class);
        }
        throw new Exception("Failed get job by id");
    }

    @SneakyThrows
    public static Job create(String token, Job job) {
        String jsonString = new Gson().toJson(job);
        request = requestJsonBuilder(token)
                .url(fullUrl("/api/job/create"))
                .post(requestBody(jsonString))
                .build();

        Response response = call(request);
        if(response.isSuccessful()) {
            return new Gson().fromJson(response.body().string(), Job.class);
        }
        throw new Exception("Failed create job");
    }

    @SneakyThrows
    public static String delete(String token, int id) {
        request = requestJsonBuilder(token)
                .url(fullUrl("/api/job/delete/" + id))
                .post(requestBody(""))
                .build();

        Response response = call(request);
        if(response.isSuccessful()) {
            return jsonResponse(response).get("message").toString();
        }
        throw new Exception("Failed delete job");
    }
}
