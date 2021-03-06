package com.furianrt.itunesmusicapp.main.fragments.albumlist.adapter;

import android.support.v7.util.DiffUtil;

import com.furianrt.itunesmusicapp.data.model.Album;

public class AlbumLIstDiffCallback extends DiffUtil.ItemCallback<Album> {

    @Override
    public boolean areItemsTheSame(Album oldItem, Album newItem) {
        return oldItem.getCollectionId() == newItem.getCollectionId();
    }

    @Override
    public boolean areContentsTheSame(Album oldItem, Album newItem) {
        return oldItem.getArtistName().equals(newItem.getArtistName()) &&
                oldItem.getCollectionName().equals(newItem.getCollectionName());
    }
}
