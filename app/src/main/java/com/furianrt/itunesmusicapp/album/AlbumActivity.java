package com.furianrt.itunesmusicapp.album;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.furianrt.itunesmusicapp.R;
import com.furianrt.itunesmusicapp.album.adapter.AlbumAdapter;
import com.furianrt.itunesmusicapp.data.model.Album;
import com.furianrt.itunesmusicapp.data.model.Track;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class AlbumActivity extends AppCompatActivity implements AlbumActivityContract.View {

    private static final String BUNDLE_TRACKS = "tracks";

    public static final String EXTRA_ALBUM = "album";

    @BindView(R.id.text_album)
    TextView mTextViewAlbum;

    @BindView(R.id.text_band)
    TextView mTextViewBand;

    @BindView(R.id.text_genre)
    TextView mTextViewGenre;

    @BindView(R.id.text_country)
    TextView mTextViewCountry;

    @BindView(R.id.text_year)
    TextView mTextViewYear;

    @BindView(R.id.text_price)
    TextView mTextViewPrice;

    @BindView(R.id.image_album)
    ImageView mImageViewAlbum;

    @BindView(R.id.list_tracks)
    RecyclerView mRecyclerViewTracks;

    @Inject
    AlbumActivityContract.Presenter mPresenter;

    private ArrayList<Track> mTracks;
    private Album mAlbum;
    private AlbumAdapter mListAdapter = new AlbumAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenterComponent(this).inject(this);
        setContentView(R.layout.activity_album);

        ButterKnife.bind(this);

        mPresenter.attachView(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (savedInstanceState != null) {
            mTracks = savedInstanceState.getParcelableArrayList(BUNDLE_TRACKS);
        }

        mAlbum = getIntent().getParcelableExtra(EXTRA_ALBUM);

        mPresenter.onViewCreate(mTracks, mAlbum.getCollectionId());

        setupUi();
    }

    private void setupUi() {
        Picasso.get()
                .load(mAlbum.getArtworkUrl100())
                .placeholder(R.drawable.ic_image)
                .into(mImageViewAlbum);

        mTextViewAlbum.setText(mAlbum.getCollectionName());
        mTextViewBand.setText(mAlbum.getArtistName());
        mTextViewGenre.setText(mAlbum.getPrimaryGenreName());
        mTextViewCountry.setText(mAlbum.getCountry());
        mTextViewYear.setText(mAlbum.getReleaseDate().substring(0, 4));
        String price = mAlbum.getCollectionPrice() + " " + mAlbum.getCurrency();
        mTextViewPrice.setText(price);

        mRecyclerViewTracks.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewTracks.setAdapter(mListAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(BUNDLE_TRACKS, mTracks);
    }

    @Override
    public void showTrackList(List<Track> tracks) {
        mTracks = new ArrayList<>(tracks);
        mListAdapter.submitList(mTracks);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
