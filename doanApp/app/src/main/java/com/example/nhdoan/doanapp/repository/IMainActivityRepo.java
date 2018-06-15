package com.example.nhdoan.doanapp.repository;

import com.example.nhdoan.doanapp.model.serverResponse.LanguageResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface IMainActivityRepo {
    Observable<LanguageResponse> getLanguageResponseFromServer();

    Observable<ResponseBody> submitFile(String filePath);
}
