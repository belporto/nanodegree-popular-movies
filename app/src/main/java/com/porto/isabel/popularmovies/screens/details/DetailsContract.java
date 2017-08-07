package com.porto.isabel.popularmovies.screens.details;

import com.porto.isabel.popularmovies.screens.details.domain.ScreenContent;
import com.porto.isabel.popularmovies.model.moviedb.Movie;
import com.porto.isabel.popularmovies.model.moviedb.Video;
import com.porto.isabel.popularmovies.network.ReviewResult;

import rx.Observable;

public interface DetailsContract {

    interface PresenterContract {

        void onCreate();

        void onPlayTrailerClicked();

        void onShowReviewClicked();

        void onFavouriteClicked();

        void onCreateMenu();
    }

    interface ViewContract {

        void init(Movie movie);

        void setReviewSize(String size);

        void showLoading();

        void setFavourite(boolean favourite);
    }

    interface InteractorContract {

        Movie getMovie();

        Observable<ScreenContent> getScreenContent();

        Video getTrailer();

        ReviewResult getReview();

        //TODO: content provider
        void setFavourite();

        boolean isFavourite();
    }

    interface Router {

        void openYoutubeVideo(String id);

        void openReviewsScreen(ReviewResult review);
    }
}
