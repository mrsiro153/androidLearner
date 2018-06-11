package com.example.nhdoan.doanapp.repository;

import com.example.nhdoan.doanapp.model.serverResponse.LanguageResponse;

import io.reactivex.Observable;

public interface IMainActivityRepo {
    Observable<LanguageResponse> getLanguageResponseFromServer();
}
