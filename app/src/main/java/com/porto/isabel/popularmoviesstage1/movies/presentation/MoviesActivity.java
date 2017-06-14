package com.porto.isabel.popularmoviesstage1.movies.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.porto.isabel.popularmoviesstage1.AppApplication;
import com.porto.isabel.popularmoviesstage1.R;
import com.porto.isabel.popularmoviesstage1.di.AppComponent;
import com.porto.isabel.popularmoviesstage1.model.moviedb.Movie;
import com.porto.isabel.popularmoviesstage1.movies.MoviesContract;
import com.porto.isabel.popularmoviesstage1.movies.di.DaggerMoviesComponent;
import com.porto.isabel.popularmoviesstage1.movies.di.MoviesModule;

import java.util.List;

import javax.inject.Inject;

public class MoviesActivity extends AppCompatActivity implements MoviesContract.ViewContract {

    @Inject
    MoviesContract.PresenterContract mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppComponent appComponent = ((AppApplication) getApplication()).getAppComponent();
        DaggerMoviesComponent.builder().appComponent(appComponent).moviesModule(
                new MoviesModule(this)).build().inject(this);

        setContentView(R.layout.activity_home);
        mPresenter.onCreate();
    }


    @Override
    public void showMovies(List<Movie> movies) {
        Log.d("TAG", movies.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
