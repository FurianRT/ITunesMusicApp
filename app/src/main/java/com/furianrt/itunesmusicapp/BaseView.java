package com.furianrt.itunesmusicapp;

import android.content.Context;

import com.furianrt.itunesmusicapp.di.presenter.PresenterComponent;
import com.furianrt.itunesmusicapp.di.presenter.PresenterModule;

public interface BaseView {

    default PresenterComponent getPresenterComponent(Context context) {
        return ((MyApp) context.getApplicationContext()).getAppComponent()
                .newPresenterComponent(new PresenterModule(context));
    }
}
