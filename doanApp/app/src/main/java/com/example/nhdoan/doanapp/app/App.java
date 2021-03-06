package com.example.nhdoan.doanapp.app;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.example.nhdoan.doanapp.di.DaggerIMyComponent;
import com.example.nhdoan.doanapp.di.IMyComponent;
import com.example.nhdoan.doanapp.ui.screenRecord.RecordService;
import com.google.firebase.storage.FirebaseStorage;

public class App extends Application{
    private final String TAG = "DOANNH";

    public IMyComponent daggerIMyComponent;
    private static App application;
    public FirebaseStorage storage;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initial();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        this.storage= storage;
    }

    private void initial(){
        //RetrofitAPI r = RetrofitAPI.getInstance(getBaseContext());
        //flexTrackAPI = r.getClient().create(BackEndAPI.class);
        daggerIMyComponent = DaggerIMyComponent.builder()
                .build();
        daggerIMyComponent.inject(this);
        //
        //
        startService(new Intent(this, RecordService.class));
        Log.w(TAG,"INIT APP DONE");
    }
    public static App getInstance() {
        return application;
    }
}
