package com.porto.isabel.popularmovies.screens.details.domain;

import com.porto.isabel.popularmovies.screens.details.DetailsContract;
import com.porto.isabel.popularmovies.model.moviedb.Movie;
import com.porto.isabel.popularmovies.model.moviedb.Video;
import com.porto.isabel.popularmovies.network.MovieDBApi;
import com.porto.isabel.popularmovies.network.ReviewResult;
import com.porto.isabel.popularmovies.network.VideosResult;

import java.util.List;

import rx.Observable;
import rx.functions.Func2;

public class DetailsInteractor implements DetailsContract.InteractorContract {

    private Movie mMovie;
    private MovieDBApi mMovieDBApi;
    private VideosResult mVideoResult;
    private ReviewResult mReviewResult;
    private Video firstTrailer;

    public DetailsInteractor(MovieDBApi movieDBApi, Movie movie) {
        mMovieDBApi = movieDBApi;
        mMovie = movie;
    }

    @Override
    public Movie getMovie() {
        return mMovie;
    }

    @Override
    public Observable<ScreenContent> getScreenContent() {

        return Observable.zip(mMovieDBApi.getVideos(mMovie.getId()), mMovieDBApi.getReviews(mMovie.getId()), new Func2<VideosResult, ReviewResult, ScreenContent>() {
            @Override
            public ScreenContent call(VideosResult videosResult, ReviewResult reviewResult) {
                mVideoResult = videosResult;
                mReviewResult = reviewResult;
                return new ScreenContent(videosResult, reviewResult);
            }
        });
    }


    @Override
    public Video getTrailer() {
        if (firstTrailer == null) {
            if (mVideoResult != null) {
                List<Video> videos = mVideoResult.getResults();
                if (videos != null && !videos.isEmpty()) {
                    firstTrailer = videos.get(0);
                }
            }
        }
        return firstTrailer;
    }

    @Override
    public ReviewResult getReview() {
        return mReviewResult;
    }

}
