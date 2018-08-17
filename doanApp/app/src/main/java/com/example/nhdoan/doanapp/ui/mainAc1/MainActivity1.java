package com.example.nhdoan.doanapp.ui.mainAc1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.nhdoan.doanapp.R;

public class MainActivity1 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);
        //
        Button btnGridList = findViewById(R.id.btn_list_grid_view);
        btnGridList.setOnClickListener(v->{

        });
    }
}
