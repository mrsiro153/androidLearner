package com.example.nhdoan.doanapp.ui.programAc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.nhdoan.doanapp.R;
import com.example.nhdoan.doanapp.app.App;
import com.example.nhdoan.doanapp.model.serverResponse.LanguageResponse;
import com.example.nhdoan.doanapp.ui.mainAc.MainActivity;
import com.example.nhdoan.doanapp.widget.ProgramingLanguageAdapter;
import com.google.gson.Gson;

import javax.inject.Inject;

public class ProgramingActivity extends AppCompatActivity{
    private static final String TAG = "DOANNH";

    private LanguageResponse languageResponse;
    private ProgramingLanguageAdapter adapter;

    @Inject
    Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_activity);
        ((App)getApplication()).daggerIMyComponent.inject(this);

        //
        RecyclerView lstPrograminglanguage = findViewById(R.id.lst_programing_language);

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            this.languageResponse = gson.fromJson(extras.getString(MainActivity.Key),LanguageResponse.class);
        }
        //
        SetupRecyclerList(lstPrograminglanguage);
        adapter.notifyDataSetChanged();
    }




    private void SetupRecyclerList(RecyclerView recyclerView){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.w(TAG,"dataset: "+languageResponse.toStringLanguage());

        adapter = new ProgramingLanguageAdapter(languageResponse.getChoices());
        recyclerView.setAdapter(adapter);

    }
}
