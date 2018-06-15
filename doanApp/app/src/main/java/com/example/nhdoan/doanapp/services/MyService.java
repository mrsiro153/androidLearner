package com.example.nhdoan.doanapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;


public class MyService extends Service {

    public static final String TAG = "DOANNH";
    private MyService instance = null;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"oncreate in service");
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                //Log.e(TAG,"hello timer: "+new Date());
            }
        }, 2000, 5000);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"onstart Command in service");
        return super.onStartCommand(intent, flags, startId);
    }
    //
}
