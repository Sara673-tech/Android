package com.example.eyedetectionapp.detection;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DetectionApiService {

    @Multipart
    @POST("api/eye-classify/")
    Call<DetectionResponse> uploadFile(
            @Part MultipartBody.Part conjunctivity_model_img,
            @Part MultipartBody.Part edc_model_img
    );

}
