package com.porto.isabel.popularmovies.details.domain;

import com.porto.isabel.popularmovies.details.DetailsContract;
import com.porto.isabel.popularmovies.model.moviedb.Movie;

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
