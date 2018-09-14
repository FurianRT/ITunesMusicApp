package com.furianrt.itunesmusicapp.di.presenter;

import android.content.Context;

import com.furianrt.itunesmusicapp.data.DataManager;
import com.furianrt.itunesmusicapp.main.MainActivityContract;
import com.furianrt.itunesmusicapp.main.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    private Context mContext;

    public PresenterModule(Context context) {
        mContext = context;
    }

    @Provides
    @PresenterScope
    MainActivityContract.Presenter provideStartPresenter(DataManager dataManager) {
        return new MainActivityPresenter(dataManager);
    }

}
