package com.furianrt.itunesmusicapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Track {

    @SerializedName("trackId")
    @Expose
    private long trackId;

    @SerializedName("trackName")
    @Expose
    private String trackName;

    @SerializedName("trackViewUrl")
    @Expose
    private String trackViewUrl;

    @SerializedName("previewUrl")
    @Expose
    private String previewUrl;

    @SerializedName("artworkUrl30")
    @Expose
    private String artworkUrl30;

    @SerializedName("trackPrice")
    @Expose
    private float trackPrice;

    @SerializedName("trackNumber")
    @Expose
    private int trackNumber;

    @SerializedName("trackTimeMillis")
    @Expose
    private long trackTimeMillis;

    public long getTrackId() {
        return trackId;
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getArtworkUrl30() {
        return artworkUrl30;
    }

    public void setArtworkUrl30(String artworkUrl30) {
        this.artworkUrl30 = artworkUrl30;
    }

    public float getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(float trackPrice) {
        this.trackPrice = trackPrice;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public long getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public void setTrackTimeMillis(long trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }
}
