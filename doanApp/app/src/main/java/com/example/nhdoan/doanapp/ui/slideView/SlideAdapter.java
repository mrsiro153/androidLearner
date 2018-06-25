package com.example.nhdoan.doanapp.ui.slideView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nhdoan.doanapp.R;

import java.util.ArrayList;

public class SlideAdapter extends PagerAdapter {
    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;

    public SlideAdapter(ArrayList<Integer> images, Context context) {
        this.images = images;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View myImageLayout = inflater.inflate(R.layout.item_slide,container,false);
        ImageView mImage = (ImageView) myImageLayout.findViewById(R.id.image_change);
        mImage.setImageResource(images.get(position));
        container.addView(myImageLayout,0);
        return myImageLayout;
    }
}

