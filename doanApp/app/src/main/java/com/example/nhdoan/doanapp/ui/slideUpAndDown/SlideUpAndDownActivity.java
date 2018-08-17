package com.example.nhdoan.doanapp.ui.slideUpAndDown;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.nhdoan.doanapp.R;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SlideUpAndDownActivity extends AppCompatActivity {

    Animation slideUpAnimation, slideDownAnimation;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_updown);
        slideUpAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_up);
        slideDownAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);
        //
        recyclerView = findViewById(R.id.lst_parallax_test);
        List<String> demo = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            demo.add(i + "   hello");
        }
        //
        ParallaxRecyclerAdapter<String> sla = new ParallaxRecyclerAdapter<String>(demo) {
            @Override
            public void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, ParallaxRecyclerAdapter<String> parallaxRecyclerAdapter, int i) {
                ((TextView) viewHolder.itemView).setText(demo.get(i));
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, ParallaxRecyclerAdapter<String> parallaxRecyclerAdapter, int i) {
                return new SimpleViewHolder(getLayoutInflater().inflate(android.R.layout.simple_list_item_1, viewGroup, false));
            }

            @Override
            public int getItemCountImpl(ParallaxRecyclerAdapter<String> parallaxRecyclerAdapter) {
                return demo.size();
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        sla.setParallaxHeader(getLayoutInflater().inflate(R.layout.layout_header, recyclerView, false), recyclerView);
        sla.setOnParallaxScroll(new ParallaxRecyclerAdapter.OnParallaxScroll() {
                                    @Override
                                    public void onParallaxScroll(float v, float v1, View view) {
                                        Log.e("LOG", "DOANNH abc Scroll percentage: " + v + "   offset: " + v1 + "   view: " + view.getMeasuredHeight());

                                    }
                                }
        );
        sla.setOnClickEvent(new ParallaxRecyclerAdapter.OnClickEvent() {
            @Override
            public void onClick(View view, int i) {
                Log.e("LOG", "DOANNH abc onclick: " + view + "    and postition: " + i);
            }
        });
        recyclerView.setAdapter(sla);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    static class SimpleViewHolder extends RecyclerView.ViewHolder {

        public SimpleViewHolder(View itemView) {
            super(itemView);
        }
    }
}
