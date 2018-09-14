package com.furianrt.itunesmusicapp.album.adapter;

import android.support.v7.util.DiffUtil;

import com.furianrt.itunesmusicapp.data.model.Track;

public class AlbumDiffCallback extends DiffUtil.ItemCallback<Track> {

    @Override
    public boolean areItemsTheSame(Track oldItem, Track newItem) {
        return oldItem.getTrackId() == newItem.getTrackId();
    }

    @Override
    public boolean areContentsTheSame(Track oldItem, Track newItem) {
        return oldItem.getTrackNumber() == newItem.getTrackNumber()
                && oldItem.getTrackName().equals(newItem.getTrackName());
    }
}
