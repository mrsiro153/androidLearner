package com.example.nhdoan.doanapp.ui.mainAc;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.nhdoan.doanapp.R;
import com.example.nhdoan.doanapp.app.App;
import com.example.nhdoan.doanapp.broadcastnh.MyBroadCastReceiver;
import com.example.nhdoan.doanapp.constant.ConstantValue;
import com.example.nhdoan.doanapp.model.serverResponse.LanguageResponse;
import com.example.nhdoan.doanapp.popup.PopupActivity;
import com.example.nhdoan.doanapp.services.MyService;
import com.example.nhdoan.doanapp.ui.beaconAc.BeaconActivity;
import com.example.nhdoan.doanapp.ui.mapActivity.MapsActivity;
import com.example.nhdoan.doanapp.ui.mapActivity.StreetViewActivity;
import com.example.nhdoan.doanapp.ui.multiList.MultiListItemActivity;
import com.example.nhdoan.doanapp.ui.playVideo.ActivityPlayVideo;
import com.example.nhdoan.doanapp.ui.programAc.ProgramingActivity;
import com.example.nhdoan.doanapp.ui.screenRecord.ScreenRecordActivity;
import com.example.nhdoan.doanapp.ui.slideUpAndDown.SlideUpAndDownActivity;
import com.example.nhdoan.doanapp.ui.slideView.SlideViewActivity;
import com.example.nhdoan.doanapp.ui.speechRecognition.ActivityRecognizeSpeech;
import com.example.nhdoan.doanapp.ultility.ScreenShot;
import com.example.nhdoan.doanapp.ultility.email.GMailSender;
import com.example.nhdoan.doanapp.widget.DialogProgramAc;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.instacart.library.truetime.extensionrx.TrueTimeRx;

import org.apache.commons.net.time.TimeTCPClient;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements IMainActivityPresenter {
    public static final String TAG_NAME = "doanNH";
    public static final String BROADCAST_NAME = "com.example.broadcast.MY_BROADCAST";
    public static final String Key = "ABC";
    TextToSpeech t1;

    public boolean filterTest = true;

    MyBroadCastReceiver myBroadCastReceiver;

    @Inject
    public IMainActivityInteractor mainActivityInteractor;

    @Inject
    Gson gson;

    private static void onClick(android.view.View v) {
    }

    //callback function

    @Override
    public void getResponseFromServer(LanguageResponse languageResponse) {
        //show new activity
        Intent i = new Intent(this, ProgramingActivity.class);
        i.putExtra(Key, gson.toJson(languageResponse));
        startActivity(i);

    }


    //#end region

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(MyService.TAG, "Main Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App) getApplication()).daggerIMyComponent.inject(this);
        mainActivityInteractor.setMainActivityPresenter(this);
        initSomeThing();
        //
        myBroadCastReceiver = new MyBroadCastReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(myBroadCastReceiver, new IntentFilter(BROADCAST_NAME));
        //
        Button btn = findViewById(R.id.btn_test);
        btn.setOnClickListener(v -> {
            getRealTime();
        });
        //
        Button btnStartBeacon = findViewById(R.id.btn_start_beacon);
        btnStartBeacon.setOnClickListener(v -> {
            Intent i = new Intent(this, BeaconActivity.class);
            startActivity(i);
        });
        //
        Button btnStartTimer = findViewById(R.id.btn_start_timer);
        btnStartTimer.setOnClickListener(v -> {
            Log.i(MyService.TAG, "starting service.....");
            getApplicationContext().startService(new Intent(this, MyService.class));
            Log.i(MyService.TAG, "end app class");
        });
        //
        Button btnSendingBroadCast = findViewById(R.id.btn_start_broadcast);
        btnSendingBroadCast.setOnClickListener(v -> {
            Intent i = new Intent(BROADCAST_NAME);
            i.setAction(BROADCAST_NAME);
            //i.setPackage("com.example.nhdoan.doanapp.broadcastnh");
            i.putExtra("data", "hello broadcast");
            //i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            getApplicationContext().sendBroadcast(i);
            Log.e(TAG_NAME, "send Broadcast success!!");
        });
        //
        Button btnShowPopUp = findViewById(R.id.btn_show_popup);
        btnShowPopUp.setOnClickListener(v -> {
            showPopUp();
        });
        //
        Log.i(TAG_NAME, "doannh initialize true time");
        Button btnGetTrueTime = findViewById(R.id.btn_get_true_time);
        btnGetTrueTime.setOnClickListener(v -> {
            getTrueTime();
        });
        //
        Button btnCallAPI = findViewById(R.id.btn_call_api);
        btnCallAPI.setOnClickListener(v -> {
            callAPI();
        });
        //
        Button btnCaptureScreen = findViewById(R.id.btn_capture_screen);
        btnCaptureScreen.setOnClickListener(v -> {
            captureScreen();
        });
        //
        Button btnRecordScreen = findViewById(R.id.btn_recording_screen);
        btnRecordScreen.setOnClickListener(v -> {
            recordScreen();
        });
        //
        Button btnPlayVideo = findViewById(R.id.btn_play_video);
        btnPlayVideo.setOnClickListener(v -> {
            goToPlayVideo();
        });
        //
        Button btnVoiceDetection = findViewById(R.id.btn_go_to_speech);
        btnVoiceDetection.setOnClickListener(v -> {
            goToVoiceDetectionScreen();
        });
        //
        Button btnUploadFile = findViewById(R.id.btn_upload_file);
        btnUploadFile.setOnClickListener(v -> {
            uploadFile();
        });
        //
        Button btnOpenSlideView = findViewById(R.id.btn_open_slide_view);
        btnOpenSlideView.setOnClickListener(v -> {
            openSlideView();
        });
        //
        Button btnUploadGoogle = findViewById(R.id.btn_upload_file_to_google_storage);
        btnUploadGoogle.setOnClickListener(v -> {
            uploadToGoogleStorage();
        });
        //
        Button btnTextToSpeech = findViewById(R.id.btn_text_to_speech);
        btnTextToSpeech.setOnClickListener(v -> {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put(TextToSpeech.Engine.KEY_PARAM_VOLUME, "1");
            t1.speak("Hello world", TextToSpeech.QUEUE_FLUSH, params);
        });
        //
        Button btnShowListDialog = findViewById(R.id.btn_show_list_dialog_fragment);
        btnShowListDialog.setOnClickListener(v -> {
            showListPopup();
        });
        //
        Button btnSlideUpAndDown = findViewById(R.id.btn_open_slide_up_down_ac);
        btnSlideUpAndDown.setOnClickListener(v -> {
            Intent i = new Intent(this, SlideUpAndDownActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_down, R.anim.fade_out);
        });
        //
        Button btnGoogleMap = findViewById(R.id.btn_google_map_ex);
        btnGoogleMap.setOnClickListener(v -> {
            Intent i = new Intent(this, MapsActivity.class);
            startActivity(i);
        });
        //
        Button btnGoogleMapStreetView = findViewById(R.id.btn_google_map_street_view);
        btnGoogleMapStreetView.setOnClickListener(v -> {
            Intent i = new Intent(this, StreetViewActivity.class);
            startActivity(i);
        });
        //
        Button btnOpenListMultiItem = findViewById(R.id.btn_multi_item_list);
        btnOpenListMultiItem.setOnClickListener(v -> {
            Intent i = new Intent(this, MultiListItemActivity.class);
            startActivity(i);
        });
        //
        Button btnSendEmail = findViewById(R.id.btn_send_email);
        btnSendEmail.setOnClickListener(v -> {
            sendEmail();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initSomeThing() {
        TrueTimeRx.build()
                .initialize(Arrays.asList("time.google.com"))
                .subscribeOn(rx.schedulers.Schedulers.io())
                .subscribe(date -> {
                    Log.e(TAG_NAME, "REALTIME IS : " + date.toString());
                }, err -> {
                    Log.e(TAG_NAME, "Error get true time", err);
                });
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                ConstantValue.MY_PERMISSIONS_REQUEST_READ_WRITE_EXTERNAL);
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
    }

    //
    private void timerFunction() {
        //
        CompositeDisposable d = new CompositeDisposable();
        //
        Log.d(TAG_NAME, "inside dosmth function");
        Observable.timer(5, TimeUnit.SECONDS)
                .filter(x -> filterTest)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(rs -> {
                    Log.e(TAG_NAME, "give me smt to do");
                }, er -> {
                }, () -> {
                    Log.i(TAG_NAME, "Complete the stream timer!");
                });
        Log.d(TAG_NAME, "after observable timer");
        //
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        filterTest = false;
        Log.d(TAG_NAME, "filterTest: " + filterTest);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        filterTest = true;
        Log.d(TAG_NAME, "filterTest time 2: " + filterTest);
    }

    //
    private void getRealTime() {
        Observable.create(it -> {
            it.onNext(getTimethroughInternet());
            it.onComplete();
        }).subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread())
                .blockingSubscribe(rs -> {
                    Log.e(MyService.TAG, "DOANNH result call: " + rs.toString());
                }, er -> {
                    Log.e(MyService.TAG, "error", er);
                });
        Log.e(MyService.TAG, "DOANNH AFTER Call GETTIMG");

    }

    //
    private Date getTimethroughInternet() {
        try {
            TimeTCPClient client = new TimeTCPClient();
            try {
                // Set timeout of 60 seconds
                client.setDefaultTimeout(60000);
                // Connecting to time server
                // Other time servers can be found at : http://tf.nist.gov/tf-cgi/servers.cgi#
                // Make sure that your program NEVER queries a server more frequently than once every 4 seconds
                client.connect("time.nist.gov");
                return client.getDate();
            } finally {
                client.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    private void showPopUp() {
        FragmentManager fm = getSupportFragmentManager();
        PopupActivity p = new PopupActivity();
        p.setCancelable(false);
        p.show(fm, "Sample diaglog");
    }

    private void getTrueTime() {
        //init when app start

        //Date date = TrueTime.now();//call only one time

        Date date;
        try {
            date = TrueTimeRx.now();
        } catch (IllegalStateException e) {
            Log.e(TAG_NAME, "error get true time after init", e);
            date = new Date();
        }
        //
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        String result = df.format(date);
        Log.i(TAG_NAME, "current date: " + result + "  current date with your UTC: " + date);
    }

    private void callAPI() {
        mainActivityInteractor.callToServer();
    }

    private void captureScreen() {
        ScreenShot.getScreenShotInstance(this.getApplicationContext()).takeScreenShot(this.findViewById(R.id.btn_capture_screen));
        //check folder picture
    }

    private void recordScreen() {
        Intent i = new Intent(this, ScreenRecordActivity.class);
        startActivity(i);
    }

    private void goToPlayVideo() {
        Intent i = new Intent(this, ActivityPlayVideo.class);
        startActivity(i);
    }

    private void goToVoiceDetectionScreen() {
        Intent i = new Intent(this, ActivityRecognizeSpeech.class);
        startActivity(i);
    }

    private void uploadFile() {
        mainActivityInteractor.submitFile("fdfdfdfd");
    }

    private void openSlideView() {
        Intent i = new Intent(this, SlideViewActivity.class);
        startActivity(i);
    }

    private void uploadToGoogleStorage() {
        Log.i(TAG_NAME, "DOANNH CURRENT time: " + new Date().toString());

        FirebaseStorage storage = ((App) getApplication()).storage;
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        path += "/abcdef";
        Log.i(TAG_NAME, "DOANNH path: " + path);
        Uri uri = Uri.fromFile(new File(path));
        StorageReference storageReference = storage.getReference().child("videos/" + uri.getLastPathSegment());
        //
        storageReference.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.w(TAG_NAME, "DOANNH SUCCESS upload");
                        Log.i(TAG_NAME, "DOANNH CURRENT time: " + new Date().toString());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG_NAME, "Error", e);
                    }
                });

    }

    //
    private void showListPopup() {
        DialogProgramAc.appear(getSupportFragmentManager());
    }

    private void sendEmail() {
        Disposable d = Observable.create(vv -> {
            GMailSender g = new GMailSender("nhdoan@mmsofts.com", "MMS123456");
            g.sendMail("For test send email", "Hello world!", "nhdoan", "ndkhoi@mmsofts.com");
            vv.onNext("");
            vv.onComplete();
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sc -> {
                    Log.w(TAG_NAME, "OK SEND EMAIL SUCCESS");
                }, er -> {
                    Log.e(TAG_NAME, "Error send email", er);
                });
    }
}
