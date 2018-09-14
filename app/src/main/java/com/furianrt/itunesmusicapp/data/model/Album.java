package com.furianrt.itunesmusicapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album implements Parcelable {

    @SerializedName("artistId")
    @Expose
    private long artistId;

    @SerializedName("collectionId")
    @Expose
    private long collectionId;

    @SerializedName("amgArtistId")
    @Expose
    private long amgArtistId;

    @SerializedName("artistName")
    @Expose
    private String artistName;

    @SerializedName("collectionName")
    @Expose
    private String collectionName;

    @SerializedName("artistViewUrl")
    @Expose
    private String artistViewUrl;

    @SerializedName("collectionViewUrl")
    @Expose
    private String collectionViewUrl;

    @SerializedName("artworkUrl60")
    @Expose
    private String artworkUrl60;

    @SerializedName("artworkUrl100")
    @Expose
    private String artworkUrl100;

    @SerializedName("collectionPrice")
    @Expose
    private float collectionPrice;

    @SerializedName("copyright")
    @Expose
    private String copyright;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("currency")
    @Expose
    private String currency;

    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;

    @SerializedName("primaryGenreName")
    @Expose
    private String primaryGenreName;

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    protected Album(Parcel in) {
        artistId = in.readLong();
        collectionId = in.readLong();
        amgArtistId = in.readLong();
        artistName = in.readString();
        collectionName = in.readString();
        artistViewUrl = in.readString();
        collectionViewUrl = in.readString();
        artworkUrl60 = in.readString();
        artworkUrl100 = in.readString();
        collectionPrice = in.readFloat();
        copyright = in.readString();
        country = in.readString();
        currency = in.readString();
        releaseDate = in.readString();
        primaryGenreName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(artistId);
        dest.writeLong(collectionId);
        dest.writeLong(amgArtistId);
        dest.writeString(artistName);
        dest.writeString(collectionName);
        dest.writeString(artistViewUrl);
        dest.writeString(collectionViewUrl);
        dest.writeString(artworkUrl60);
        dest.writeString(artworkUrl100);
        dest.writeFloat(collectionPrice);
        dest.writeString(copyright);
        dest.writeString(country);
        dest.writeString(currency);
        dest.writeString(releaseDate);
        dest.writeString(primaryGenreName);
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(long collectionId) {
        this.collectionId = collectionId;
    }

    public long getAmgArtistId() {
        return amgArtistId;
    }

    public void setAmgArtistId(long amgArtistId) {
        this.amgArtistId = amgArtistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public void setArtistViewUrl(String artistViewUrl) {
        this.artistViewUrl = artistViewUrl;
    }

    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    public void setCollectionViewUrl(String collectionViewUrl) {
        this.collectionViewUrl = collectionViewUrl;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public float getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(float collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }
}
