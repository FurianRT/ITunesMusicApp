package com.furianrt.itunesmusicapp.main;

import com.furianrt.itunesmusicapp.BasePresenter;
import com.furianrt.itunesmusicapp.BaseView;
import com.furianrt.itunesmusicapp.data.model.Album;

import java.util.List;

public interface MainActivityContract {

    interface View extends BaseView {

        void showAlbumList(List<Album> albums);

        void showLoadingIndicator();

        void hideLoadingIndicator();

        void showNetworkError();

        void showEmptyAlbumList();

        void openAlbumView(Album album);
    }

    interface Presenter extends BasePresenter<View> {

        void onSearchQuerySubmit(String query, boolean networkAvailable);

        void onAlbumClick(Album album, boolean networkAvailable);
    }
}
