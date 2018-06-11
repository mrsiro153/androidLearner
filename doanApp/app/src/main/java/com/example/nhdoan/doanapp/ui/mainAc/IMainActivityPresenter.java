package com.example.nhdoan.doanapp.ui.mainAc;

import com.example.nhdoan.doanapp.model.serverResponse.LanguageResponse;

public interface IMainActivityPresenter {
    void getResponseFromServer(LanguageResponse languageResponse);
}
