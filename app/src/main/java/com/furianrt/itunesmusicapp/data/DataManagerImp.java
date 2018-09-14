package com.furianrt.itunesmusicapp.data;

import com.furianrt.itunesmusicapp.data.api.ITunesApiService;
import com.furianrt.itunesmusicapp.data.model.Album;
import com.furianrt.itunesmusicapp.data.model.ApiAlbumsResponse;
import com.furianrt.itunesmusicapp.data.model.ApiTracksResponse;
import com.furianrt.itunesmusicapp.data.model.Track;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DataManagerImp implements DataManager {

    private ITunesApiService mApiService;

    public DataManagerImp(ITunesApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Single<List<Album>> getAlbumList(String albumName) {
        //Сортировка списка альбомов
        return mApiService.getAlbumList(albumName)
                .map(ApiAlbumsResponse::getAlbums)
                .flatMapObservable(Observable::fromIterable)
                .toSortedList((a1, a2) -> a1.getCollectionName().compareTo(a2.getCollectionName()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<List<Track>> getTracksForAlbum(Long albumId) {
        return mApiService.getTracksForAlbum(albumId)
                .map(ApiTracksResponse::getTracks)
                .flatMapObservable(Observable::fromIterable)
                .filter(track -> track.getWrapperType().equals("track"))
                .toSortedList((t1, t2) -> t1.getTrackNumber() - t2.getTrackNumber())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
