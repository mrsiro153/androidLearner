package com.example.nhdoan.doanapp.services;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import android.util.Log;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;

import java.util.Collection;

public class BeaconDetectionService implements  RangeNotifier, BeaconConsumer {
    private Context context;
    private static String TAG = "DOANNH TAG";
    private BeaconManager beaconManager;
    private Region region = new Region("B9407F30-F5F8-466E-AFF9-25556B57FE6D",
            Identifier.parse("B9407F30-F5F8-466E-AFF9-25556B57FE6D"),
            Identifier.fromInt(11219),
            Identifier.fromInt(20629));

    public BeaconDetectionService(Context context) {
        this.context = context;
        beaconManager= BeaconManager.getInstanceForApplication(context);
        beaconManager.bind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.addMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {

            }

            @Override
            public void didExitRegion(Region region) {

            }

            @Override
            public void didDetermineStateForRegion(int i, Region region) {

            }
        });
        beaconManager.addRangeNotifier(this);
        try {
            beaconManager.startRangingBeaconsInRegion(region);
            beaconManager.startMonitoringBeaconsInRegion(region);
        }catch (RemoteException e){
            Log.e(TAG,"Error",e);
        }
    }

    @Override
    public void unbindService(ServiceConnection serviceConnection) {
        context.unbindService(serviceConnection);
    }

    @Override
    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
        return context.bindService(intent, serviceConnection, i);
    }

    @Override
    public Context getApplicationContext() {
        return context;
    }

    @Override
    public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
        if (collection.size() > 0) {
            Log.e(TAG, "The first beacon I see is about " + collection.iterator().next().getDistance() + " meters away.");
        } else {
            Log.e(TAG, "I don't see any beacon");
        }
    }
}
