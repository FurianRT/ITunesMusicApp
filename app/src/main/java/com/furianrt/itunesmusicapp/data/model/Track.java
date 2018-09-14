package com.furianrt.itunesmusicapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Track implements Parcelable {

    @SerializedName("wrapperType")
    @Expose
    private String wrapperType;

    @SerializedName("artistId")
    @Expose
    private int artistId;
    @SerializedName("collectionId")
    @Expose
    private int collectionId;
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
    @SerializedName("trackCount")
    @Expose
    private int trackCount;
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
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("trackId")
    @Expose
    private int trackId;
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
    @SerializedName("trackNumber")
    @Expose
    private int trackNumber;

    protected Track(Parcel in) {
        wrapperType = in.readString();
        artistId = in.readInt();
        collectionId = in.readInt();
        artistName = in.readString();
        collectionName = in.readString();
        artistViewUrl = in.readString();
        collectionViewUrl = in.readString();
        artworkUrl60 = in.readString();
        artworkUrl100 = in.readString();
        collectionPrice = in.readFloat();
        trackCount = in.readInt();
        copyright = in.readString();
        country = in.readString();
        currency = in.readString();
        releaseDate = in.readString();
        primaryGenreName = in.readString();
        kind = in.readString();
        trackId = in.readInt();
        trackName = in.readString();
        trackViewUrl = in.readString();
        previewUrl = in.readString();
        artworkUrl30 = in.readString();
        trackNumber = in.readInt();
    }

    public static final Creator<Track> CREATOR = new Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
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

    public String getWrapperType() {
        return wrapperType;
    }

    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
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

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
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

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(wrapperType);
        dest.writeInt(artistId);
        dest.writeInt(collectionId);
        dest.writeString(artistName);
        dest.writeString(collectionName);
        dest.writeString(artistViewUrl);
        dest.writeString(collectionViewUrl);
        dest.writeString(artworkUrl60);
        dest.writeString(artworkUrl100);
        dest.writeFloat(collectionPrice);
        dest.writeInt(trackCount);
        dest.writeString(copyright);
        dest.writeString(country);
        dest.writeString(currency);
        dest.writeString(releaseDate);
        dest.writeString(primaryGenreName);
        dest.writeString(kind);
        dest.writeInt(trackId);
        dest.writeString(trackName);
        dest.writeString(trackViewUrl);
        dest.writeString(previewUrl);
        dest.writeString(artworkUrl30);
        dest.writeInt(trackNumber);
    }
}
