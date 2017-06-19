package com.porto.isabel.popularmoviesstage1.movies.presentation;

import android.app.Activity;
import android.content.Intent;

import com.porto.isabel.popularmoviesstage1.details.presentation.DetailsActivity;
import com.porto.isabel.popularmoviesstage1.model.moviedb.Movie;
import com.porto.isabel.popularmoviesstage1.movies.MoviesContract;

/**
 * Created by belporto on 19/06/2017.
 */

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
