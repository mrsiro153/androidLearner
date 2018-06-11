package com.example.nhdoan.doanapp.broadcastnh;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadCastReceiver extends BroadcastReceiver {
    private static final String TAG = "DOANNH";
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"HELLO WORLD",Toast.LENGTH_LONG).show();
        Log.e(TAG,"action Intent: "+intent.getAction());
        Log.e(TAG,"My Broadcast receiver RECEIVED");
    }

}
