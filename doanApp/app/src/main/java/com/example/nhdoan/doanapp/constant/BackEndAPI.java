package com.example.nhdoan.doanapp.constant;


import com.example.nhdoan.doanapp.model.serverResponse.LanguageResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface BackEndAPI {
    @GET("questions")
    Observable<LanguageResponse> getDOANNH();

    @Multipart
    @POST("file")
    Observable<ResponseBody> submitFile(@Part MultipartBody.Part file);

}
