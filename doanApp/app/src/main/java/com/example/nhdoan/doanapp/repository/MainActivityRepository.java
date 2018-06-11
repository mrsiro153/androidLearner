package com.example.nhdoan.doanapp.repository;

import android.util.Log;

import com.example.nhdoan.doanapp.constant.BackEndAPI;
import com.example.nhdoan.doanapp.model.serverResponse.LanguageResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MainActivityRepository implements IMainActivityRepo {
    private BackEndAPI backEndAPI;

    @Inject
    public MainActivityRepository(
                    BackEndAPI backEndAPI) {

        this.backEndAPI = backEndAPI;
    }

    @Override
    public Observable<LanguageResponse> getLanguageResponseFromServer() {
        Log.w("DOANNH", "instance of backEndAPI: " + backEndAPI);
        return backEndAPI.getDOANNH();
    }
}
