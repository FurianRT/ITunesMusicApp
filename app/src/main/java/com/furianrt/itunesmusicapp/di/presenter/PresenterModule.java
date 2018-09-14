package com.furianrt.itunesmusicapp.di.presenter;

import android.content.Context;

import com.furianrt.itunesmusicapp.album.AlbumActivityContract;
import com.furianrt.itunesmusicapp.album.AlbumActivityPresenter;
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
    MainActivityContract.Presenter provideMainActivityPresenter(DataManager dataManager) {
        return new MainActivityPresenter(dataManager);
    }

    @Provides
    @PresenterScope
    AlbumActivityContract.Presenter provideAlbumActivityPresenter(DataManager dataManager) {
        return new AlbumActivityPresenter(dataManager);
    }
}
