package com.porto.isabel.popularmovies.screens.movies.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.porto.isabel.popularmovies.AppApplication;
import com.porto.isabel.popularmovies.R;
import com.porto.isabel.popularmovies.di.AppComponent;
import com.porto.isabel.popularmovies.model.moviedb.Movie;
import com.porto.isabel.popularmovies.screens.movies.MoviesContract;
import com.porto.isabel.popularmovies.screens.movies.di.DaggerMoviesComponent;
import com.porto.isabel.popularmovies.screens.movies.di.MoviesModule;
import com.porto.isabel.popularmovies.screens.movies.domain.SortBy;
import com.porto.isabel.popularmovies.screens.movies.presentation.adapter.MoviesAdapter;

import java.util.List;

import javax.inject.Inject;

public class MoviesActivity extends AppCompatActivity implements MoviesContract.View, MoviesAdapter.MoviesAdapterOnClickHandler {

    private static final String TAG = MoviesActivity.class.getSimpleName();
    public static final int REQUEST_CODE = 101;
    @Inject
    MoviesContract.Presenter mPresenter;

    private RecyclerView mRecyclerView;
    private MoviesAdapter mMoviesAdapter;
    private View mErrorView;
    private View mEmptyView;
    private ProgressBar mProgressBar;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppComponent appComponent = ((AppApplication) getApplication()).getAppComponent();
        DaggerMoviesComponent.builder().appComponent(appComponent).moviesModule(
                new MoviesModule(this)).build().inject(this);

        setContentView(R.layout.activity_movies);
        mPresenter.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.movies_toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        mProgressBar = (ProgressBar) findViewById(R.id.movies_loading);
        mErrorView = findViewById(R.id.movies_error);
        mEmptyView = findViewById(R.id.movies_empty);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.movies_swipe_refresh);

        int noOfColumns = calculateNoOfColumns();
        GridLayoutManager layoutManager
                = new GridLayoutManager(this, noOfColumns);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mMoviesAdapter = new MoviesAdapter(noOfColumns, this);
        mRecyclerView.setAdapter(mMoviesAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(() -> mPresenter.onRefresh());


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }


    @Override
    public void showMovies(List<Movie> movies) {
        mMoviesAdapter.setData(movies);
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setRefreshing(false);
        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void showSortByOptions(SortBy sortBy) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = getLayoutInflater().inflate(R.layout.sort_by, null);

        View mostPopular = sheetView.findViewById(R.id.most_popular);
        View topRated = sheetView.findViewById(R.id.top_rated);
        View fav = sheetView.findViewById(R.id.fav);
        View mostPopularCheck = sheetView.findViewById(R.id.most_popular_check);
        View topRatedCheck = sheetView.findViewById(R.id.top_rated_check);
        View favCheck = sheetView.findViewById(R.id.fav_check);

        if (sortBy == SortBy.MOST_POPULAR) {
            mostPopularCheck.setVisibility(View.VISIBLE);
            topRatedCheck.setVisibility(View.INVISIBLE);
            favCheck.setVisibility(View.INVISIBLE);
        } else if (sortBy == SortBy.TOP_RATED) {
            mostPopularCheck.setVisibility(View.INVISIBLE);
            topRatedCheck.setVisibility(View.VISIBLE);
            favCheck.setVisibility(View.INVISIBLE);
        } else {
            mostPopularCheck.setVisibility(View.INVISIBLE);
            topRatedCheck.setVisibility(View.INVISIBLE);
            favCheck.setVisibility(View.VISIBLE);
        }

        mostPopular.setOnClickListener(v -> {
            mPresenter.onSortOptionClicked(SortBy.MOST_POPULAR);
            bottomSheetDialog.dismiss();
        });

        topRated.setOnClickListener(v ->
        {
            mPresenter.onSortOptionClicked(SortBy.TOP_RATED);
            bottomSheetDialog.dismiss();
        });

        fav.setOnClickListener(v ->
        {
            mPresenter.onSortOptionClicked(SortBy.FAVOURITES);
            bottomSheetDialog.dismiss();
        });


        bottomSheetDialog.setContentView(sheetView);
        bottomSheetDialog.show();
    }

    @Override
    public void showEmptyState() {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sort_by) {
            mPresenter.onSortByClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(Movie movie) {
        Log.d(TAG, "Movie clicked - " + movie.getId());
        mPresenter.onMovieClicked(movie);
    }

    private int calculateNoOfColumns() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 120;
        return (int) (dpWidth / scalingFactor);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            mPresenter.onActivityResult();
        }
    }
}
