package com.furianrt.itunesmusicapp.main;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.furianrt.itunesmusicapp.R;
import com.furianrt.itunesmusicapp.album.AlbumActivity;
import com.furianrt.itunesmusicapp.data.model.Album;
import com.furianrt.itunesmusicapp.main.fragments.albumlist.AlbumListFragment;
import com.furianrt.itunesmusicapp.main.fragments.emptylist.EmptyListFragment;
import com.furianrt.itunesmusicapp.main.fragments.hello.HelloFragment;
import com.furianrt.itunesmusicapp.main.fragments.networkerror.NetworkErrorFragment;
import com.furianrt.itunesmusicapp.utils.NetworkUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View,
        SearchView.OnQueryTextListener, AlbumListFragment.OnListFragmentInteractionListener {

    private static final String BUNDLE_SEARCH_QUERY = "searchQuery";

    @Inject
    MainActivityContract.Presenter mPresenter;

    private String mSearchQuery;
    private Dialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getPresenterComponent(this).inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mPresenter.attachView(this);

        if (savedInstanceState != null) {
            mSearchQuery = savedInstanceState.getString(BUNDLE_SEARCH_QUERY);
        } else {
            //если activity создается в первый раз, показываем экран приветсвия
            FragmentManager fm = getSupportFragmentManager();
            String tag = HelloFragment.class.getName();
            if (fm.findFragmentByTag(tag) == null) {
                fm.beginTransaction()
                        .add(R.id.fragment_container, new HelloFragment(), tag)
                        .commit();
            }
        }

        setupUi();
    }

    private void setupUi() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLoadingDialog = new AlertDialog.Builder(this)
                .setView(R.layout.dialog_loading)
                .setCancelable(false)
                .create();

        mLoadingDialog.getWindow()
                .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        //Восстановление поискового запроса после пересоздания activity
        if (mSearchQuery != null && !mSearchQuery.isEmpty()) {
            search.expandActionView();
            searchView.setQuery(mSearchQuery, false);
        }

        search.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                mSearchQuery = null;
                return true;
            }
        });

        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_SEARCH_QUERY, mSearchQuery);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mSearchQuery = newText;
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        boolean networkAvailable = NetworkUtils.isNetworkAvailable(this);
        mPresenter.onSearchQuerySubmit(query, networkAvailable);
        return false;
    }

    @Override
    public void showAlbumList(List<Album> albums) {
        FragmentManager fm = getSupportFragmentManager();
        String tag = AlbumListFragment.class.getName();
        Fragment fragment = fm.findFragmentByTag(tag);
        if (fragment == null) {
            fm.beginTransaction()
                    .replace(R.id.fragment_container, AlbumListFragment.newInstance(albums), tag)
                    .commit();
        } else {
            ((AlbumListFragment) fragment).submitAlbumList(albums);
        }
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
    public void showNetworkError() {
        FragmentManager fm = getSupportFragmentManager();
        String tag = NetworkErrorFragment.class.getName();
        if (fm.findFragmentByTag(tag) == null) {
            fm.beginTransaction()
                    .replace(R.id.fragment_container, new NetworkErrorFragment(), tag)
                    .commit();
        }
    }

    @Override
    public void showEmptyAlbumList() {
        FragmentManager fm = getSupportFragmentManager();
        String tag = EmptyListFragment.class.getName();
        if (fm.findFragmentByTag(tag) == null) {
            fm.beginTransaction()
                    .replace(R.id.fragment_container, new EmptyListFragment(), tag)
                    .commit();
        }
    }

    @Override
    public void onAlbumClick(Album album) {
        boolean networkAvailable = NetworkUtils.isNetworkAvailable(this);
        mPresenter.onAlbumClick(album, networkAvailable);
    }

    @Override
    public void openAlbumView(Album album) {
        Intent intent = new Intent(this, AlbumActivity.class);
        intent.putExtra(AlbumActivity.EXTRA_ALBUM, album);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
