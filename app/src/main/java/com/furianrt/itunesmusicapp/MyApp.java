package com.furianrt.itunesmusicapp;

import android.app.Application;
import android.support.annotation.NonNull;

import com.furianrt.itunesmusicapp.di.application.AppComponent;
import com.furianrt.itunesmusicapp.di.application.AppModule;
import com.furianrt.itunesmusicapp.di.application.DaggerAppComponent;

import timber.log.Timber;

public class MyApp extends Application {

    private static final String LOG_TAG = "myTag";

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree(){
                @Override
                protected void log(int priority, String tag, @NonNull String message, Throwable t) {
                    super.log(priority, LOG_TAG, message, t);
                }

            });
        }
    }

    public AppComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return mAppComponent;
    }
}
