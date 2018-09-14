package com.furianrt.itunesmusicapp.album;

import com.furianrt.itunesmusicapp.data.DataManager;
import com.furianrt.itunesmusicapp.data.model.Track;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class AlbumActivityPresenter implements AlbumActivityContract.Presenter {

    private AlbumActivityContract.View mView;
    private DataManager mDataManager;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public AlbumActivityPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(AlbumActivityContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mCompositeDisposable.clear();
        mView = null;
    }

    @Override
    public void onViewCreate(List<Track> tracks, long albumId) {
        if (tracks == null) {
            loadTrackList(albumId);
        } else {
            mView.showTrackList(tracks);
        }
    }

    private void loadTrackList(long albumId) {
        Disposable disposable = mDataManager.getTracksForAlbum(albumId)
                .subscribe(tracks -> {
                    if (mView != null) {
                        mView.showTrackList(tracks);
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
