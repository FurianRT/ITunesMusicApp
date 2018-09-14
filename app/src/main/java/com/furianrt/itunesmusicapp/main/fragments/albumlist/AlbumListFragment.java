package com.furianrt.itunesmusicapp.main.fragments.albumlist;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.furianrt.itunesmusicapp.R;
import com.furianrt.itunesmusicapp.data.model.Album;
import com.furianrt.itunesmusicapp.main.fragments.albumlist.adapter.AlbumListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumListFragment extends Fragment
        implements AlbumListAdapter.OnAlbumListItemInteractionListener {

    private static final int VERTICAL_LIST_SPAN_COUNT = 3;
    private static final int HORIZONTAL_LIST_SPAN_COUNT = 2;
    private static final String BUNDLE_ALBUM_LIST = "albumList";
    private static final String BUNDLE_RECYCLER_VIEW_POSITION = "recyclerPosition";

    @BindView(R.id.list_albums)
    RecyclerView mRecyclerViewAlbums;

    private ArrayList<Album> mAlbumList;
    private AlbumListAdapter mListAdapter = new AlbumListAdapter(this);
    private OnListFragmentInteractionListener mListener;



    public static AlbumListFragment newInstance(List<Album> albums) {
        AlbumListFragment fragment = new AlbumListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(BUNDLE_ALBUM_LIST, new ArrayList<>(albums));
        fragment.setArguments(args);
        return fragment;
    }

    public void submitAlbumList(List<Album> albumList) {
        mAlbumList = new ArrayList<>(albumList);
        mListAdapter.submitList(mAlbumList);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_list, container, false);

        ButterKnife.bind(this, view);

        //В зависимости от ориентации, изменяется направление промотки RecyclerView
        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mRecyclerViewAlbums.setLayoutManager(new GridLayoutManager(getContext(),
                    HORIZONTAL_LIST_SPAN_COUNT, GridLayoutManager.HORIZONTAL, false));
        } else {
            mRecyclerViewAlbums.setLayoutManager(new GridLayoutManager(getContext(),
                    VERTICAL_LIST_SPAN_COUNT, GridLayoutManager.VERTICAL, false));
        }

        mRecyclerViewAlbums.setAdapter(mListAdapter);

        if (savedInstanceState != null) {
            //Восстановление состояния RecyclerView
            mAlbumList = savedInstanceState.getParcelableArrayList(BUNDLE_ALBUM_LIST);
            mListAdapter.submitList(mAlbumList);
            Parcelable listState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_VIEW_POSITION);
            mRecyclerViewAlbums.getLayoutManager().onRestoreInstanceState(listState);
        } else if (getArguments() != null) {
            mAlbumList = getArguments().getParcelableArrayList(BUNDLE_ALBUM_LIST);
            mListAdapter.submitList(mAlbumList);
        } else {
            throw new IllegalArgumentException();
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(BUNDLE_ALBUM_LIST, mAlbumList);

        outState.putParcelable(BUNDLE_RECYCLER_VIEW_POSITION,
                mRecyclerViewAlbums.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onAlbumListItemClick(Album album) {
        mListener.onAlbumClick(album);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {

        void onAlbumClick(Album album);
    }
}
