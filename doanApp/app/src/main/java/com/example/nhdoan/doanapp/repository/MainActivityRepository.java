package com.example.nhdoan.doanapp.repository;

import android.util.Log;

import com.example.nhdoan.doanapp.constant.BackEndAPI;
import com.example.nhdoan.doanapp.model.serverResponse.LanguageResponse;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

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

    @Override
    public Observable<ResponseBody> submitFile(String filePath) {
        File file = new File(filePath);


        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part p = MultipartBody.Part.createFormData("image",file.getName(),requestFile);
        return backEndAPI.submitFile(p);
    }
}
