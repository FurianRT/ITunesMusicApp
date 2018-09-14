package com.furianrt.itunesmusicapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ApiTracksResponse {

    @SerializedName("resultCount")
    @Expose
    private int mTracksCount;

    @SerializedName("results")
    @Expose
    private List<Track> mTracks = new ArrayList<>();

    public int getTracksCount() {
        return mTracksCount;
    }

    public void setTracksCount(int tracksCount) {
        mTracksCount = tracksCount;
    }

    public List<Track> getTracks() {
        return mTracks;
    }

    public void setResults(List<Track> tracks) {
        mTracks = tracks;
    }
}
