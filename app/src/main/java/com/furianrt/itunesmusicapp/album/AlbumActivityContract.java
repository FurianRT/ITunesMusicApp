package com.furianrt.itunesmusicapp.album;

import com.furianrt.itunesmusicapp.BasePresenter;
import com.furianrt.itunesmusicapp.BaseView;
import com.furianrt.itunesmusicapp.data.model.Track;

import java.util.List;

public interface AlbumActivityContract {

    interface View extends BaseView {

        void showTrackList(List<Track> tracks);
    }

    interface Presenter extends BasePresenter<View> {


        void onViewCreate(List<Track> tracks, long albumId);
    }
}
