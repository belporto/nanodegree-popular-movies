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
    private SortBy mSortBy = SortBy.MOST_POPULAR;
    private List<Movie> mMovies;

    public MoviesInteractor(MovieDBApi movieDBApi, FavouritesRepository favouritesRepository) {
        mMovieDBApi = movieDBApi;
        mFavRepo = favouritesRepository;
    }

    @Override
    public Observable<List<Movie>> getMovies(SortBy sortBy) {
        if (sortBy == mSortBy && mMovies != null && !mMovies.isEmpty()) {
            return Observable.just(mMovies);
        } else {
            mMovies = null;
            mSortBy = sortBy;
            if (sortBy == SortBy.MOST_POPULAR) {
                return mMovieDBApi.getPopularMovies().map(MovieResult::getResults);
            } else if (sortBy == SortBy.TOP_RATED) {
                return mMovieDBApi.getTopRatedMovies().map(MovieResult::getResults);
            } else {
                return Observable.defer(() -> Observable.just(mFavRepo.getFavourites()));
            }
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

    @Override
    public void setMovies(List<Movie> movies) {
        mMovies = movies;
    }

    @Override
    public List<Movie> getMovies() {
        return mMovies;
    }
}
