package com.example.nhdoan.doanapp.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nhdoan.doanapp.R;

import java.util.List;

public class MultiItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> lst;

    public MultiItemAdapter(List<String> lst) {
        this.lst = lst;
    }

    //
    public static class ViewHolder1 extends RecyclerView.ViewHolder {
        private View itemView;

        public ViewHolder1(View itemView) {
            super(itemView);
            this.itemView = itemView;
            setUpView();
        }

        //
        private void setUpView() {

        }
    }

    //
    public static class ViewHolder2 extends RecyclerView.ViewHolder {
        private View itemView;

        public ViewHolder2(View itemView) {
            super(itemView);
            this.itemView = itemView;
            setUpView();
        }

        private void setUpView() {

        }
    }
    //


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e("DOANNH","VIEW TYPE IS: "+viewType);
        switch (viewType) {
            case 0: {
                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_programing_language, parent, false);
                return new ViewHolder1(v);
            }
            default: {
                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_multi_lst, parent, false);
                return new ViewHolder2(v);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.e("DOANNH", "NHDOAN CALL onBindViewHolder");
        switch (holder.getItemViewType()) {
            case 0:
                TextView t = ((ViewHolder1) holder).itemView.findViewById(R.id.txt_choice);
                t.setText(lst.get(position));
                break;
            default:
                TextView t1 = ((ViewHolder2) holder).itemView.findViewById(R.id.txt_multi_text);
                t1.setText(lst.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

}
