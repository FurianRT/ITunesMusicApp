package com.furianrt.itunesmusicapp.album.adapter;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.furianrt.itunesmusicapp.R;
import com.furianrt.itunesmusicapp.data.model.Track;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumAdapter extends ListAdapter<Track, AlbumAdapter.AlbumViewHolder> {

    public AlbumAdapter() {
        super(new AlbumDiffCallback());
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_album_list_item, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        holder.bindData(getItem(position));
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_track_number)
        TextView mTextViewTrackNumber;

        @BindView(R.id.text_track_name)
        TextView mTextViewTrackName;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(Track track) {
            mTextViewTrackName.setText(track.getTrackName());
            mTextViewTrackNumber.setText(String.valueOf(track.getTrackNumber()));
        }
    }
}
