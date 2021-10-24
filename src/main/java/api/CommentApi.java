package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Comment;
import lombok.SneakyThrows;
import okhttp3.Response;

import java.util.List;

public class CommentApi extends BaseApi {
    @SneakyThrows
    public static Comment create(String token, int jobId, Comment comment) {
        String jsonString = new Gson().toJson(comment);
        request = requestJsonBuilder(token)
                .url(fullUrl("/api/comment/" +jobId + "/create"))
                .post(requestBody(jsonString))
                .build();

        Response response = call(request);
        if(response.isSuccessful()) {
            return new Gson().fromJson(response.body().string(), Comment.class);
        }
        throw new Exception("Failed create comment for job id:" + jobId);
    }

    @SneakyThrows
    public static List<Comment> getAllForJob(String token, int jobId) {
        request = requestJsonBuilder(token)
                .url(fullUrl("/api/comment/" +jobId + "/all"))
                .build();
        Response response = call(request);
        if(response.isSuccessful()) {
            return new Gson().fromJson(response.body().string(), new TypeToken<List<Comment>>(){}.getType());
        }
        throw new Exception("Failed create comment for job id:" + jobId);
    }
}
