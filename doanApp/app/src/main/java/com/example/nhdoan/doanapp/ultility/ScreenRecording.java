package com.example.nhdoan.doanapp.ultility;

import android.app.Activity;
import android.content.Context;

public class ScreenRecording {
    private Context context;
    private Activity activity;

    private ScreenRecording(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }
    public ScreenRecording getMyInstance(Context context,Activity activity){
        return new ScreenRecording(context,activity);
    }

}
