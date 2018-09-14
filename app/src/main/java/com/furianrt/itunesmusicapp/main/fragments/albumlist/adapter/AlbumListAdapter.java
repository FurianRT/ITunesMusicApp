package com.furianrt.itunesmusicapp.main.fragments.albumlist.adapter;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.furianrt.itunesmusicapp.R;
import com.furianrt.itunesmusicapp.data.model.Album;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumListAdapter extends ListAdapter<Album, AlbumListAdapter.AlbumListViewHolder> {

    private OnAlbumListItemInteractionListener mListener;

    public AlbumListAdapter(OnAlbumListItemInteractionListener listener) {
        super(new AlbumLIstDiffCallback());
        mListener = listener;
    }

    @NonNull
    @Override
    public AlbumListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_album_list_item, parent, false);
        return new AlbumListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumListViewHolder holder, int position) {
        holder.bindData(getItem(position));
    }

    public interface OnAlbumListItemInteractionListener {

        void onAlbumListItemClick(Album album);
    }

    class AlbumListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.text_album_name)
        TextView mTextViewAlbumName;

        @BindView(R.id.text_artist_name)
        TextView mTextViewArtistName;

        @BindView(R.id.image_album_preview)
        ImageView mImageViewAlbum;

        private Album mAlbum;

        public AlbumListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindData(Album album) {
            mAlbum = album;
            mTextViewAlbumName.setText(album.getCollectionName());
            mTextViewArtistName.setText(album.getArtistName());

            Picasso.get()
                    .load(album.getArtworkUrl100())
                    .placeholder(R.drawable.ic_image)
                    .into(mImageViewAlbum);
        }

        @Override
        public void onClick(View v) {
            mListener.onAlbumListItemClick(mAlbum);
        }
    }
}
