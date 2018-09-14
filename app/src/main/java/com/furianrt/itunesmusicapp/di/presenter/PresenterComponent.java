package com.furianrt.itunesmusicapp.di.presenter;

import com.furianrt.itunesmusicapp.main.MainActivity;

import dagger.Subcomponent;

@PresenterScope
@Subcomponent(modules = PresenterModule.class)
public interface PresenterComponent {

    void inject(MainActivity activity);


}
