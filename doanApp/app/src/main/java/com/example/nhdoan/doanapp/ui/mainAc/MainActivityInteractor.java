package com.example.nhdoan.doanapp.ui.mainAc;

import android.util.Log;

import com.example.nhdoan.doanapp.repository.IMainActivityRepo;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityInteractor implements IMainActivityInteractor{
    private static final String TAG_NAME = "DOANNH";

    private IMainActivityRepo mainActivityRepository;
    private IMainActivityPresenter mainActivityPresenter;


    @Inject
    public MainActivityInteractor(IMainActivityRepo mainActivityRepository) {
        this.mainActivityRepository = mainActivityRepository;
    }

    @Override
    public void setMainActivityPresenter(IMainActivityPresenter mainActivityPresenter) {
        this.mainActivityPresenter = mainActivityPresenter;
    }

    @Override
    public void callToServer(){
        Disposable d = mainActivityRepository.getLanguageResponseFromServer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next -> {
                            //Log.i(TAG_NAME, "code and response: "+next.string());
                            mainActivityPresenter.getResponseFromServer(next);
                        },
                        err -> {
                            Log.e(TAG_NAME,"error get programing language",err);
                        });
        //do nothing here
    }

    @Override
    public void submitFile(String filePath) {
        Disposable d = mainActivityRepository.submitFile(filePath)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next->{
                    Log.i(TAG_NAME,"submit file success");
                },err->{
                    Log.e(TAG_NAME,"Error submit file",err);
                });
    }
}
