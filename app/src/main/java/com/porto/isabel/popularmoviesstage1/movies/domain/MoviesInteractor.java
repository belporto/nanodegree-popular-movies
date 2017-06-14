package com.porto.isabel.popularmoviesstage1.movies.domain;

import com.porto.isabel.popularmoviesstage1.model.moviedb.Movie;
import com.porto.isabel.popularmoviesstage1.network.MovieDBApi;
import com.porto.isabel.popularmoviesstage1.network.MovieResult;
import com.porto.isabel.popularmoviesstage1.movies.MoviesContract;

import java.util.List;

import rx.Observable;

/**
 * Created by belporto on 11/06/2017.
 */

public class MoviesInteractor implements MoviesContract.InteractorContract {


    private MovieDBApi mMovieDBApi;

    public MoviesInteractor(MovieDBApi mMovieDBApi) {
        this.mMovieDBApi = mMovieDBApi;
    }

    @Override
    public Observable<List<Movie>> getPopularMovies() {
        //TODO: handle error
        return mMovieDBApi.getPopularMovies().map(MovieResult::getResults);
    }
}
