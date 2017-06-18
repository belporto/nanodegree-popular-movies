package com.porto.isabel.popularmoviesstage1.movies.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.porto.isabel.popularmoviesstage1.AppApplication;
import com.porto.isabel.popularmoviesstage1.R;
import com.porto.isabel.popularmoviesstage1.di.AppComponent;
import com.porto.isabel.popularmoviesstage1.model.moviedb.Movie;
import com.porto.isabel.popularmoviesstage1.movies.MoviesContract;
import com.porto.isabel.popularmoviesstage1.movies.di.DaggerMoviesComponent;
import com.porto.isabel.popularmoviesstage1.movies.di.MoviesModule;
import com.porto.isabel.popularmoviesstage1.movies.presentation.adapter.MoviesAdapter;

import java.util.List;

import javax.inject.Inject;

public class MoviesActivity extends AppCompatActivity implements MoviesContract.ViewContract {

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
        mPresenter.onCreate();

        //TODO: View injection
        mRecyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        mProgressBar = (ProgressBar) findViewById(R.id.movies_loading);
        mErrorView = findViewById(R.id.movies_error);

        GridLayoutManager layoutManager
                = new GridLayoutManager(this, NUMBER_OF_COLUMNS);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mMoviesAdapter = new MoviesAdapter();
        mRecyclerView.setAdapter(mMoviesAdapter);

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
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
