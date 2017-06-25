package com.porto.isabel.popularmovies.movies.domain;

import com.porto.isabel.popularmovies.model.moviedb.Movie;
import com.porto.isabel.popularmovies.movies.MoviesContract;
import com.porto.isabel.popularmovies.network.MovieDBApi;
import com.porto.isabel.popularmovies.network.MovieResult;

import java.util.List;

import rx.Observable;

public class MoviesInteractor implements MoviesContract.InteractorContract {


    private MovieDBApi mMovieDBApi;
    private SortBy mSortBy;

    public MoviesInteractor(MovieDBApi movieDBApi) {
        mMovieDBApi = movieDBApi;
        mSortBy = SortBy.MOST_POPULAR; //Default Value
    }

    @Override
    public Observable<List<Movie>> getMovies() {
        if (mSortBy == SortBy.MOST_POPULAR) {
            return mMovieDBApi.getPopularMovies().map(MovieResult::getResults);
        } else {
            return mMovieDBApi.getTopRatedMovies().map(MovieResult::getResults);
        }
    }

    @Override
    public void setSortBy(SortBy sortBy) {
        mSortBy = sortBy;
    }

    @Override
    public SortBy getSortBy() {
        return mSortBy;
    }
}
