package com.example.nhdoan.doanapp.services;


import android.util.Log;

import com.example.nhdoan.doanapp.constant.BackEndAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAPI {
    private final static String TAGNAME = "doannh";
    private Retrofit retrofit = null;
    //
    public Retrofit getClient() {
        //
        retrofit = new Retrofit.Builder()
                .baseUrl("http://private-0d5b8-nhdoan.apiary-mock.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClient())
                .build();
        return retrofit;
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder b = new OkHttpClient.Builder();
        b.connectTimeout(30, TimeUnit.SECONDS);
        b.addInterceptor(c -> {
            String token = "fdfsfsdfsdfbsd";
            Request.Builder bRequest = c.request().newBuilder();
            bRequest.addHeader("token", token);
            return c.proceed(bRequest.build());
        });
        b.addInterceptor(c -> {
            Request request = c.request();
            Response response = c.proceed(c.request());
            ResponseBody copy = response.peekBody(Long.MAX_VALUE);
            Buffer buffer = new Buffer();
            if (request.body() != null) {
                request.body().writeTo(buffer);
            }
            Log.v(TAGNAME, "Request: " + request.url() + " tokenHeaderIs: " + request.header("token") + " with data: " + buffer.readUtf8() + " and RESPONSE FROM SERVER: code: "+
                    +response.code()+" and body:" + copy.string());
            return response;
        });
        return b.build();
    }

    public BackEndAPI getBackendAPI(){
        return getClient().create(BackEndAPI.class);
    }
}
