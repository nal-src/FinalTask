package api;

import com.google.gson.Gson;
import entity.Image;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.File;

public class ImageApi extends BaseApi {
    @SneakyThrows
    public static String upload(String token, File file) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(MediaType.parse("image/jpeg"), file))
                .build();

        request = requestMultipartFormBuilder(token)
                .url(fullUrl("/api/image/upload"))
                .post(requestBody)
                .build();

        Response response = call(request);
        if(response.isSuccessful()) {
            return jsonResponse(response).get("message").toString();
        }
        throw new Exception("Failed upload image");
    }

    @SneakyThrows
    public static Image getImageByUser(String token) {
        request = requestJsonBuilder(token)
                .url(fullUrl("/api/image/profile"))
                .build();

        Response response = call(request);
        if(response.isSuccessful()) {
            return new Gson().fromJson(response.body().string(), Image.class);
        }
        throw new Exception("Failed get user image");
    }
}
