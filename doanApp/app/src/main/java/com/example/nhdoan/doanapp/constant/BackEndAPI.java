package com.example.nhdoan.doanapp.constant;


import com.example.nhdoan.doanapp.model.serverResponse.LanguageResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface BackEndAPI {
    @GET("questions")
    Observable<LanguageResponse> getDOANNH();

}
