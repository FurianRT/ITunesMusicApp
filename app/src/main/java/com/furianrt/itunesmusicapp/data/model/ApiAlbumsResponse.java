package com.furianrt.itunesmusicapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ApiAlbumsResponse {

    @SerializedName("resultCount")
    @Expose
    private int mAlbumsCount;

    @SerializedName("results")
    @Expose
    private List<Album> mAlbums = new ArrayList<>();

    public List<Album> getAlbums() {
        return mAlbums;
    }

    public void setAlbums(List<Album> albums) {
        mAlbums = albums;
    }

    public int getAlbumsCount() {
        return mAlbumsCount;
    }

    public void setAlbumsCount(int count) {
        mAlbumsCount = count;
    }
}
