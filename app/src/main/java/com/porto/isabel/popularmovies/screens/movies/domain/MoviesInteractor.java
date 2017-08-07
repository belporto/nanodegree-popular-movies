package com.porto.isabel.popularmovies.screens.movies.domain;

import com.porto.isabel.popularmovies.model.moviedb.Movie;
import com.porto.isabel.popularmovies.network.MovieDBApi;
import com.porto.isabel.popularmovies.network.MovieResult;
import com.porto.isabel.popularmovies.repository.FavouritesRepository;
import com.porto.isabel.popularmovies.screens.movies.MoviesContract;

import java.util.List;

import rx.Observable;

public class MoviesInteractor implements MoviesContract.Interactor {


    private final FavouritesRepository mFavRepo;
    private MovieDBApi mMovieDBApi;
    private SortBy mSortBy;

    public MoviesInteractor(MovieDBApi movieDBApi, FavouritesRepository favouritesRepository) {
        mMovieDBApi = movieDBApi;
        mSortBy = SortBy.MOST_POPULAR; //Default Value
        mFavRepo = favouritesRepository;
    }

    @Override
    public Observable<List<Movie>> getMovies() {
        if (mSortBy == SortBy.MOST_POPULAR) {
            return mMovieDBApi.getPopularMovies().map(MovieResult::getResults);
        } else if (mSortBy == SortBy.TOP_RATED) {
            return mMovieDBApi.getTopRatedMovies().map(MovieResult::getResults);
        } else {
            return Observable.defer(() -> Observable.just(mFavRepo.getFavourites()));
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
