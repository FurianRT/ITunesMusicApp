package com.furianrt.itunesmusicapp.main;

import com.furianrt.itunesmusicapp.BasePresenter;
import com.furianrt.itunesmusicapp.BaseView;
import com.furianrt.itunesmusicapp.data.model.Album;

import java.util.List;

public interface MainActivityContract {

    interface View extends BaseView {

        void showAlbums(List<Album> albums);

        void showLoadingIndicator();

        void hideLoadingIndicator();

        void checkNetworkAvailability();

        void showNetworkError();
    }

    interface Presenter extends BasePresenter<View> {

        void onSearchQuerySubmit(String query);

        void onAlbumClick(Album album);

        void onNetworkAvailabilityResult(boolean networkAvailable);
    }
}
