package com.example.nhdoan.doanapp.di;

import com.example.nhdoan.doanapp.constant.BackEndAPI;
import com.example.nhdoan.doanapp.repository.IMainActivityRepo;
import com.example.nhdoan.doanapp.repository.MainActivityRepository;
import com.example.nhdoan.doanapp.ui.mainAc.IMainActivityInteractor;
import com.example.nhdoan.doanapp.ui.mainAc.MainActivityInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    @Singleton
    IMainActivityRepo provideMainActivityRepo(BackEndAPI backEndAPI){
        return new MainActivityRepository(backEndAPI);
    }

    @Provides
    @Singleton
    IMainActivityInteractor provideActivityInteractor(IMainActivityRepo mainActivityRepo){
        return new MainActivityInteractor(mainActivityRepo);
    }
}
