package com.simpleplayer.railianmaksym.simpleplayer;

import android.app.Application;

import com.simpleplayer.railianmaksym.simpleplayer.di.AppComponent;
import com.simpleplayer.railianmaksym.simpleplayer.di.AudioModule;
import com.simpleplayer.railianmaksym.simpleplayer.di.DaggerAppComponent;


public class MyApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent= DaggerAppComponent.builder()
                .audioModule(new AudioModule(this))
                .build();


    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
