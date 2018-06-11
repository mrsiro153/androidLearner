package com.example.nhdoan.doanapp.ui.mainAc;

public interface IMainActivityInteractor {
    void setMainActivityPresenter(IMainActivityPresenter mainActivityPresenter);
    void callToServer();
}
