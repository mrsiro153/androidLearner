package com.example.nhdoan.doanapp.widget;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;

import java.util.List;

public class StringListAdapter extends ParallaxRecyclerAdapter<String>{

    public StringListAdapter(List<String> data) {
        super(data);
    }

    @Override
    public void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, ParallaxRecyclerAdapter<String> parallaxRecyclerAdapter, int i) {
        ((TextView) viewHolder.itemView).setText(getData().get(i));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, ParallaxRecyclerAdapter<String> parallaxRecyclerAdapter, int i) {
        return new SimpleViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_1, viewGroup, false));
    }

    @Override
    public int getItemCountImpl(ParallaxRecyclerAdapter<String> parallaxRecyclerAdapter) {
        return 0;
    }
    //
    static class SimpleViewHolder extends RecyclerView.ViewHolder {

        public SimpleViewHolder(View itemView) {
            super(itemView);
        }
    }
}
