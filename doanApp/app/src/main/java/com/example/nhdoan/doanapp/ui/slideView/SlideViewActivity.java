package com.example.nhdoan.doanapp.ui.slideView;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.nhdoan.doanapp.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SlideViewActivity extends AppCompatActivity {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN = {R.drawable.ic_play_button, R.drawable.ic_launcher_background, R.drawable.ic_play_button, R.drawable.ic_play_button, R.drawable.ic_play_button};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_view);
        //
        init();
    }

    private void init() {
        for (int i = 0; i < XMEN.length; i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlideAdapter(XMENArray, SlideViewActivity.this));
        //CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        //indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                //handler.post(Update);
            }
        }, 2500, 2500);
    }
}
