package com.furianrt.itunesmusicapp.main;

import com.furianrt.itunesmusicapp.data.DataManager;
import com.furianrt.itunesmusicapp.data.model.Album;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private MainActivityContract.View mView;
    private DataManager mDataManager;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

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
    public void onSearchQuerySubmit(String query, boolean networkAvailable) {
        if (!query.isEmpty()) {
            if (networkAvailable) {
                mView.showLoadingIndicator();
                loadAlbumList(query);
            } else {
                mView.showNetworkError();
            }
        }
    }

    private void loadAlbumList(String query) {
        Disposable disposable = mDataManager.getAlbumList(query)
                .subscribe(albums -> {
                    if (mView != null) {
                        if (albums.isEmpty()) {
                            mView.showEmptyAlbumList();
                        } else {
                            mView.showAlbumList(albums);
                        }
                        mView.hideLoadingIndicator();
                    }
                });

        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onAlbumClick(Album album, boolean networkAvailable) {
        if (networkAvailable) {
            mView.openAlbumView(album);
        } else {
            mView.showNetworkError();
        }
    }
}
