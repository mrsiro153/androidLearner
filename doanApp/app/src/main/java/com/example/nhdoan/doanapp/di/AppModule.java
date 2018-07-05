package com.example.nhdoan.doanapp.di;

import com.example.nhdoan.doanapp.app.App;
import com.example.nhdoan.doanapp.constant.BackEndAPI;
import com.example.nhdoan.doanapp.services.RetrofitAPI;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    App getApplication(){
        return new App();
    }

    @Provides
    @Singleton
    RetrofitAPI provideRetrofit(){
        return new RetrofitAPI();
    }

    @Provides
    @Singleton
    BackEndAPI provideBackEndAPI(RetrofitAPI retrofitAPI){
        return retrofitAPI.getBackendAPI();
    }

    @Provides
    @Singleton
    Gson provideGson(){
        return new Gson();
    }


}
