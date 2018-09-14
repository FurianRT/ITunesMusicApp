package com.furianrt.itunesmusicapp.main;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.furianrt.itunesmusicapp.R;
import com.furianrt.itunesmusicapp.Utils.NetworkUtils;
import com.furianrt.itunesmusicapp.data.model.Album;
import com.furianrt.itunesmusicapp.general.LoadingDialog;
import com.furianrt.itunesmusicapp.main.adapter.MainListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View,
        MainListAdapter.OnMainListItemInteractionListener, SearchView.OnQueryTextListener {

    private static final int LIST_SPAN_COUNT = 3;
    private static final String BUNDLE_ALBUM_LIST = "albumList";
    private static final String BUNDLE_RECYCLER_VIEW_POSITION = "recyclerPosition";
    private static final String BUNDLE_SEARCH_QUERY = "searchQuery";

    @BindView(R.id.list_albums)
    RecyclerView mRecyclerViewAlbums;

    @Inject
    MainActivityContract.Presenter mPresenter;

    private MainListAdapter mListAdapter = new MainListAdapter(this);
    private ArrayList<Album> mAlbumList;
    private String mSearchQuery;
    private Dialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getPresenterComponent(this).inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mPresenter.attachView(this);

        setupUi();

        if (savedInstanceState != null) {
            mSearchQuery = savedInstanceState.getString(BUNDLE_SEARCH_QUERY);
            //Восстановление состояния RecyclerView
            mAlbumList = savedInstanceState.getParcelableArrayList(BUNDLE_ALBUM_LIST);
            mListAdapter.submitList(mAlbumList);
            Parcelable listState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_VIEW_POSITION);
            mRecyclerViewAlbums.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

    private void setupUi() {
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        mRecyclerViewAlbums.setLayoutManager(new GridLayoutManager(this, LIST_SPAN_COUNT));
        mRecyclerViewAlbums.setAdapter(mListAdapter);

        mLoadingDialog = new AlertDialog.Builder(this)
                .setView(R.layout.dialog_loading)
                .setCancelable(false)
                .create();
        mLoadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        //Восстановление поискового запроса после пересоздания activity
        if (mSearchQuery != null) {
            search.expandActionView();
            searchView.setQuery(mSearchQuery, false);
        }

        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(BUNDLE_ALBUM_LIST, mAlbumList);
        outState.putParcelable(BUNDLE_RECYCLER_VIEW_POSITION,
                mRecyclerViewAlbums.getLayoutManager().onSaveInstanceState());
        outState.putString(BUNDLE_SEARCH_QUERY, mSearchQuery);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mSearchQuery = newText;
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mPresenter.onSearchQuerySubmit(query);
        return false;
    }

    @Override
    public void onMainListItemClick(Album album) {
        mPresenter.onAlbumClick(album);
    }

    @Override
    public void showAlbums(List<Album> albums) {
        mAlbumList = new ArrayList<>(albums);
        mListAdapter.submitList(albums);
    }

    @Override
    public void showLoadingIndicator() {
        mLoadingDialog.show();
    }

    @Override
    public void hideLoadingIndicator() {
        mLoadingDialog.hide();
    }

    @Override
    public void checkNetworkAvailability() {
        boolean networkAvailable = NetworkUtils.isNetworkAvailable(this);
        mPresenter.onNetworkAvailabilityResult(networkAvailable);
    }

    @Override
    public void showNetworkError() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.network_error)
                .setPositiveButton(R.string.ok, null)
                .create()
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
