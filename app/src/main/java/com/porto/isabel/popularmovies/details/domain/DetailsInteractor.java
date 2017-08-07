package com.porto.isabel.popularmovies.details.domain;

import com.porto.isabel.popularmovies.details.DetailsContract;
import com.porto.isabel.popularmovies.model.moviedb.Movie;
import com.porto.isabel.popularmovies.model.moviedb.Video;
import com.porto.isabel.popularmovies.network.MovieDBApi;
import com.porto.isabel.popularmovies.network.VideosResult;

import java.util.List;

import rx.Observable;

public class DetailsInteractor implements DetailsContract.InteractorContract {

    private Movie mMovie;
    private MovieDBApi mMovieDBApi;

    public DetailsInteractor(MovieDBApi movieDBApi, Movie movie) {
        mMovieDBApi = movieDBApi;
        mMovie = movie;
    }

    @Override
    public Movie getMovie() {
        return mMovie;
    }

    @Override
    public Observable<List<Video>> getVideos() {
        return mMovieDBApi.getVideos(mMovie.getId()).map(VideosResult::getResults);
    }

}
