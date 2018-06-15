package com.example.nhdoan.doanapp.ui.beaconAc;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;


import java.util.Collection;

public class BeaconActivity extends Activity implements BeaconConsumer {

    private final String TAG = "DOANNH BEACON";
    private BeaconManager beaconManager;
    private Region region = new Region("B9407F30-F5F8-466E-AFF9-25556B57FE6D",
            Identifier.parse("b9407f30-f5f8-466e-aff9-25556b57fe6d"),
            Identifier.fromInt(11219),
            Identifier.fromInt(20629));


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24"));
        //BeaconManager.
        beaconManager.bind(this);
        //EstimoteSDK.enableDebugLogging(true);
        //

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //beaconManager.unbind(this);
        //finish();
    }

    @Override
    public void onBeaconServiceConnect() {
        MonitorNotifier monitorNotifier = new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                Log.e(TAG, "I just saw an beacon for the first time!");
            }

            @Override
            public void didExitRegion(Region region) {
                Log.e(TAG, "I no longer see an beacon");
            }

            @Override
            public void didDetermineStateForRegion(int i, Region region) {
                Log.e(TAG, "I have just switched from seeing/not seeing beacons: " + i);
            }
        };
        beaconManager.addMonitorNotifier(monitorNotifier);

        RangeNotifier rangeNotifier = new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
                Log.e(TAG,"Collection size: "+collection.size());
                if (collection.size() > 0) {
                    Log.e(TAG, "The first beacon I see is about " + collection.iterator().next().getDistance() + " meters away.");
                } else {
                    Log.e(TAG, "I don't see any beacon");
                }
            }
        };
        beaconManager.addRangeNotifier(rangeNotifier);
        try {
            beaconManager.startRangingBeaconsInRegion(region);
            beaconManager.startMonitoringBeaconsInRegion(region);
        } catch (RemoteException e) {
            Log.e(TAG, "Error", e);
        }
    }

}
