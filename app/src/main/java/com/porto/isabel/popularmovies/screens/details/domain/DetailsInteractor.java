package com.porto.isabel.popularmovies.screens.details.domain;

import com.porto.isabel.popularmovies.model.moviedb.Movie;
import com.porto.isabel.popularmovies.model.moviedb.Video;
import com.porto.isabel.popularmovies.network.MovieDBApi;
import com.porto.isabel.popularmovies.network.ReviewResult;
import com.porto.isabel.popularmovies.network.VideosResult;
import com.porto.isabel.popularmovies.repository.FavouritesRepository;
import com.porto.isabel.popularmovies.screens.details.DetailsContract;

import java.util.List;

import rx.Observable;

public class DetailsInteractor implements DetailsContract.InteractorContract {

    private Movie mMovie;
    private MovieDBApi mMovieDBApi;
    private VideosResult mVideoResult;
    private ReviewResult mReviewResult;
    private Video mFirstTrailer;
    private FavouritesRepository mFavRepo;

    public DetailsInteractor(MovieDBApi movieDBApi, Movie movie, FavouritesRepository favouritesRepository) {
        mMovieDBApi = movieDBApi;
        mMovie = movie;
        mFavRepo = favouritesRepository;
    }

    @Override
    public Movie getMovie() {
        return mMovie;
    }

    @Override
    public Observable<ScreenContent> getScreenContent() {

        return Observable.zip(mMovieDBApi.getVideos(mMovie.getId()), mMovieDBApi.getReviews(mMovie.getId()), (videosResult, reviewResult) -> {
            mVideoResult = videosResult;
            mReviewResult = reviewResult;
            return new ScreenContent(videosResult, reviewResult);
        });
    }


    @Override
    public Video getTrailer() {
        if (mFirstTrailer == null) {
            if (mVideoResult != null) {
                List<Video> videos = mVideoResult.getResults();
                if (videos != null && !videos.isEmpty()) {
                    mFirstTrailer = videos.get(0);
                }
            }
        }
        return mFirstTrailer;
    }

    @Override
    public ReviewResult getReview() {
        return mReviewResult;
    }

    @Override
    public void setFavourite() {
        boolean isFav = mFavRepo.isFavourite(mMovie);
        if (isFav) {
            mFavRepo.deleteFavourite(mMovie);
        } else {
            mFavRepo.addFavourite(mMovie);
        }
    }

    @Override
    public boolean isFavourite() {
        return mFavRepo.isFavourite(mMovie);
    }

}
