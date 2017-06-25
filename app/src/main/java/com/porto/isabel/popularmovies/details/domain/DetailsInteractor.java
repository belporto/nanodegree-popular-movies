package com.porto.isabel.popularmovies.details.domain;

import com.porto.isabel.popularmovies.details.DetailsContract;
import com.porto.isabel.popularmovies.model.moviedb.Movie;

/**
 * Created by belporto on 25/06/2017.
 */

public class DetailsInteractor implements DetailsContract.InteractorContract {


    private Movie mMovie;

    public DetailsInteractor(Movie movie) {
        mMovie = movie;
    }

    @Override
    public Movie getMovie() {
        return mMovie;
    }

}
