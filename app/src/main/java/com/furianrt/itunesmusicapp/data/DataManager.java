package com.furianrt.itunesmusicapp.data;

import com.furianrt.itunesmusicapp.data.model.Album;
import com.furianrt.itunesmusicapp.data.model.ApiAlbumsResponse;
import com.furianrt.itunesmusicapp.data.model.ApiTracksResponse;
import com.furianrt.itunesmusicapp.data.model.Track;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface DataManager {

    Single<List<Album>> getAlbumList(String albumName);

    Single<List<Track>> getTracksForAlbum(Long albumId);

}
