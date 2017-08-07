package com.porto.isabel.popularmovies.screens.movies.presentation;

import android.app.Activity;
import android.content.Intent;

import com.porto.isabel.popularmovies.screens.details.presentation.DetailsActivity;
import com.porto.isabel.popularmovies.model.moviedb.Movie;
import com.porto.isabel.popularmovies.screens.movies.MoviesContract;

public class MoviesRouter implements MoviesContract.RouterContract {

    private final Activity mActivity;

    public MoviesRouter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void showDetails(Movie movie) {
        Intent intent = new Intent(mActivity, DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_MOVIE, movie);
        mActivity.startActivity(intent);
    }
}
