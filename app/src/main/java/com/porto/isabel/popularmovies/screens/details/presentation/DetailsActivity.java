package com.porto.isabel.popularmovies.screens.details.presentation;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.porto.isabel.popularmovies.AppApplication;
import com.porto.isabel.popularmovies.R;
import com.porto.isabel.popularmovies.screens.details.DetailsContract;
import com.porto.isabel.popularmovies.details.di.DaggerDetailsComponent;
import com.porto.isabel.popularmovies.screens.details.di.DetailsModule;
import com.porto.isabel.popularmovies.di.AppComponent;
import com.porto.isabel.popularmovies.model.moviedb.Movie;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.ViewContract {

    public static final String EXTRA_MOVIE = "com.porto.isabel.popularmovies.EXTRA_MOVIE";
    @Inject
    DetailsContract.PresenterContract mPresenter;

    private ImageView mBackDropImageView;
    private TextView mReleaseDateTextView;
    private TextView mSynopsisTextView;
    private RatingBar mUserRatingBar;
    private CollapsingToolbarLayout collapsingToolbar;
    private ProgressBar mProgress;
    private View mContentView;
    private View mPlayTrailerView;
    private TextView mReviewSize;
    private View mReviewView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        AppComponent appComponent = ((AppApplication) getApplication()).getAppComponent();
        DaggerDetailsComponent.builder().appComponent(appComponent).detailsModule(
                new DetailsModule(this, movie)).build().inject(this);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.details_toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.details_collapsing_toolbar);
        setSupportActionBar(toolbar);

        mBackDropImageView = (ImageView) findViewById(R.id.details_backdrop);
        mReleaseDateTextView = (TextView) findViewById(R.id.details_release_date);
        mSynopsisTextView = (TextView) findViewById(R.id.details_synopsis);
        mUserRatingBar = (RatingBar) findViewById(R.id.details_user_rating);
        mProgress = (ProgressBar) findViewById(R.id.details_loading);
        mContentView = findViewById(R.id.details_content);
        mPlayTrailerView = findViewById(R.id.details_play_trailer);
        mReviewView = findViewById(R.id.details_show_review);
        mReviewSize = (TextView) findViewById(R.id.details_review_size);

        mPlayTrailerView.setOnClickListener(v -> mPresenter.onPlayTrailerClicked());
        mReviewView.setOnClickListener(v -> mPresenter.onShowReviewClicked());

        mPresenter.onCreate();
    }

    @Override
    public void init(Movie movie) {
        String uri = "http://image.tmdb.org/t/p/w500" + movie.getBackdropPath();
        Picasso.with(this).load(uri).into(mBackDropImageView);

        mSynopsisTextView.setText(movie.getOverview());
        mReleaseDateTextView.setText(movie.getReleaseDate());
        mUserRatingBar.setRating(movie.getVoteAverage().floatValue() / 2);
        collapsingToolbar.setTitle(movie.getTitle());

        mProgress.setVisibility(View.GONE);
        mContentView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setReviewSize(String size) {
        mReviewSize.setText(size);
    }

    @Override
    public void showLoading() {
        mProgress.setVisibility(View.VISIBLE);
        mContentView.setVisibility(View.GONE);
    }
}
