package com.furianrt.itunesmusicapp.main;

import com.furianrt.itunesmusicapp.data.DataManager;
import com.furianrt.itunesmusicapp.data.model.Album;
import com.furianrt.itunesmusicapp.data.model.ApiAlbumsResponse;

import java.util.ArrayList;
import java.util.Collections;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private MainActivityContract.View mView;
    private DataManager mDataManager;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private String mSearchQuery;

    public MainActivityPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainActivityContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mCompositeDisposable.clear();
        mView = null;
    }

    @Override
    public void onSearchQuerySubmit(String query) {
        if (query.isEmpty()) return;
        mSearchQuery = query;
        mView.checkNetworkAvailability();
    }

    @Override
    public void onNetworkAvailabilityResult(boolean networkAvailable) {
        if (networkAvailable) {
            mView.showLoadingIndicator();
            loadAlbumList();
        } else {
            mView.showNetworkError();
        }
    }

    private void loadAlbumList() {
        Disposable disposable = mDataManager.getAlbumList(mSearchQuery)
                .subscribe(albums -> {
                    if (mView != null) {
                        mView.showAlbums(albums);
                        mView.hideLoadingIndicator();
                    }
                });

        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onAlbumClick(Album album) {
        Disposable disposable = mDataManager.getTracksForAlbum(album.getCollectionId())
                .subscribe();
        mCompositeDisposable.add(disposable);
    }
}
