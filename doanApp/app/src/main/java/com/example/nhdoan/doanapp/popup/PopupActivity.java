package com.example.nhdoan.doanapp.popup;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.example.nhdoan.doanapp.R;

public class PopupActivity extends DialogFragment {
    //properties

    //callback

    //main function

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.popup_window,container,false);
        getDialog().setTitle("My dialog");
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.w("DOANNH","view created");
        view.findViewById(R.id.btn_close_popup).setOnClickListener(v->{
            Log.w("DOANNH","close popup");
            this.dismiss();
        });
    }

}
