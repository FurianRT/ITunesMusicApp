package com.furianrt.itunesmusicapp.main.adapter;

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

public class MainListAdapter extends ListAdapter<Album, MainListAdapter.MainViewHolder> {

    private OnMainListItemInteractionListener mListener;

    public MainListAdapter(OnMainListItemInteractionListener listener) {
        super(new MainDiffCallback());
        mListener = listener;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_list_item, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.bindData(getItem(position));
    }

    public interface OnMainListItemInteractionListener {

        void onMainListItemClick(Album album);
    }

    class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.text_album_name)
        TextView mTextViewAlbumName;

        @BindView(R.id.text_artist_name)
        TextView mTextViewArtistName;

        @BindView(R.id.image_album_preview)
        ImageView mImageViewAlbum;

        private Album mAlbum;

        public MainViewHolder(View itemView) {
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
            mListener.onMainListItemClick(mAlbum);
        }
    }
}
