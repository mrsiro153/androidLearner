package com.example.nhdoan.doanapp.widget;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nhdoan.doanapp.R;
import com.example.nhdoan.doanapp.model.serverResponse.Choice;

import java.util.List;

public class ProgramingLanguageAdapter extends RecyclerView.Adapter<ProgramingLanguageAdapter.ViewHolder> {

    private List<Choice> dataset;

    public ProgramingLanguageAdapter(List<Choice> dataset) {
        this.dataset = dataset;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

    @NonNull
    @Override
    public ProgramingLanguageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_programing_language, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView choice = holder.itemView.findViewById(R.id.txt_choice);
        choice.setText(dataset.get(position).getChoice());
        //
        TextView vote = holder.itemView.findViewById(R.id.txt_vote);
        vote.setText(String.valueOf(dataset.get(position).getVotes()));
        //
        ProgressBar p = holder.itemView.findViewById(R.id.pgb_get_data);
        p.setBackgroundColor(Color.parseColor(dataset.get(position).getColor()));

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
