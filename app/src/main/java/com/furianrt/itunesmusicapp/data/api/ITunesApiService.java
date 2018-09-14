package com.furianrt.itunesmusicapp.data.api;

import com.furianrt.itunesmusicapp.data.model.ApiAlbumsResponse;
import com.furianrt.itunesmusicapp.data.model.ApiTracksResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ITunesApiService {

    @GET("search")
    Single<ApiAlbumsResponse> getAlbumList(@Query("term") String albumName);

    @GET("lookup")
    Single<ApiTracksResponse> getTracksForAlbum(@Query("id") Long albumId);
}
