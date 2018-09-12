package com.example.nhdoan.doanapp.ui.multiList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nhdoan.doanapp.R;
import com.example.nhdoan.doanapp.widget.MultiItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class MultiListItemActivity extends AppCompatActivity {
    private MultiItemAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_lst);
        //
        RecyclerView r = findViewById(R.id.lst_multi_item_type);
        //
        List<String> lst = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            lst.add("Count: " + i);
        }
        adapter = new MultiItemAdapter(lst);
        r.setLayoutManager(new LinearLayoutManager(this));
        r.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
