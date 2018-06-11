package com.example.nhdoan.doanapp.di;

import com.example.nhdoan.doanapp.ui.mainAc.MainActivity;
import com.example.nhdoan.doanapp.app.App;
import com.example.nhdoan.doanapp.ui.programAc.ProgramingActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, MainActivityModule.class})
public interface IMyComponent {
    void inject(App app);

    void inject(MainActivity mainActivity);

    void inject(ProgramingActivity programingActivity);
}
