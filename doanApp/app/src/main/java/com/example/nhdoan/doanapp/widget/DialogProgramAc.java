package com.example.nhdoan.doanapp.widget;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.nhdoan.doanapp.R;
import com.example.nhdoan.doanapp.model.serverResponse.Choice;

import java.util.ArrayList;
import java.util.Objects;

public class DialogProgramAc extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_list_recycler_view,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //
        Objects.requireNonNull(getDialog().getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
        //
        RecyclerView lst = view.findViewById(R.id.lst_dialog_program_language);
        ArrayList<Choice> datas = new ArrayList<>();
        datas.add(new Choice().setColor("#00ff00"));
        datas.add(new Choice().setColor("#aaffdd"));
        datas.add(new Choice().setColor("#fafaaf"));
        datas.add(new Choice().setColor("#deffde"));
        datas.add(new Choice().setColor("#000000"));
        datas.add(new Choice().setColor("#0000ff"));
        datas.add(new Choice().setColor("#aaddff"));

        ProgramingLanguageAdapter adapter = new ProgramingLanguageAdapter(datas);
        lst.setAdapter(adapter);
        lst.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        //
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(getDialog().getWindow())
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
    }

    //
    public static void appear(FragmentManager fragmentManager){
        DialogProgramAc d = new DialogProgramAc();
        d.show(fragmentManager,"fdfdfd");
    }
}
