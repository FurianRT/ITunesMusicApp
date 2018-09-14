package com.furianrt.itunesmusicapp.di.application;

import com.furianrt.itunesmusicapp.di.presenter.PresenterComponent;
import com.furianrt.itunesmusicapp.di.presenter.PresenterModule;

import dagger.Component;

@AppScope
@Component(modules = AppModule.class)
public interface AppComponent {

    PresenterComponent newPresenterComponent(PresenterModule module);
}
