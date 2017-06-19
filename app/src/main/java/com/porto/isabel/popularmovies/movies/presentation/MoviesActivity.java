package com.porto.isabel.popularmovies.movies.presentation;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.porto.isabel.popularmovies.AppApplication;
import com.porto.isabel.popularmovies.R;
import com.porto.isabel.popularmovies.di.AppComponent;
import com.porto.isabel.popularmovies.model.moviedb.Movie;
import com.porto.isabel.popularmovies.movies.MoviesContract;
import com.porto.isabel.popularmovies.movies.di.DaggerMoviesComponent;
import com.porto.isabel.popularmovies.movies.di.MoviesModule;
import com.porto.isabel.popularmovies.movies.domain.SortBy;
import com.porto.isabel.popularmovies.movies.presentation.adapter.MoviesAdapter;

import java.util.List;

import javax.inject.Inject;

public class MoviesActivity extends AppCompatActivity implements MoviesContract.ViewContract, MoviesAdapter.MoviesAdapterOnClickHandler {

    private static final int NUMBER_OF_COLUMNS = 3;
    @Inject
    MoviesContract.PresenterContract mPresenter;

    private RecyclerView mRecyclerView;
    private MoviesAdapter mMoviesAdapter;
    private View mErrorView;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppComponent appComponent = ((AppApplication) getApplication()).getAppComponent();
        DaggerMoviesComponent.builder().appComponent(appComponent).moviesModule(
                new MoviesModule(this)).build().inject(this);

        setContentView(R.layout.activity_movies);
        mPresenter.onCreate(savedInstanceState);

        mRecyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        mProgressBar = (ProgressBar) findViewById(R.id.movies_loading);
        mErrorView = findViewById(R.id.movies_error);

        GridLayoutManager layoutManager
                = new GridLayoutManager(this, NUMBER_OF_COLUMNS);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mMoviesAdapter = new MoviesAdapter(this);
        mRecyclerView.setAdapter(mMoviesAdapter);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }


    @Override
    public void showMovies(List<Movie> movies) {
        mMoviesAdapter.setData(movies);
        mProgressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSortByOptions(SortBy sortBy) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = getLayoutInflater().inflate(R.layout.sort_by, null);

        View mostPopular = sheetView.findViewById(R.id.most_popular);
        View topRated = sheetView.findViewById(R.id.top_rated);
        View mostPopularCheck = sheetView.findViewById(R.id.most_popular_check);
        View topRatedCheck = sheetView.findViewById(R.id.top_rated_check);

        if (sortBy == SortBy.MOST_POPULAR) {
            mostPopularCheck.setVisibility(View.VISIBLE);
            topRatedCheck.setVisibility(View.INVISIBLE);
        } else {
            mostPopularCheck.setVisibility(View.INVISIBLE);
            topRatedCheck.setVisibility(View.VISIBLE);
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


        bottomSheetDialog.setContentView(sheetView);
        bottomSheetDialog.show();
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
        mPresenter.onMovieClicked(movie);
    }
}