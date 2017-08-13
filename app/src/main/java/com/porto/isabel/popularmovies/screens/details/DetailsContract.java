package com.porto.isabel.popularmovies.screens.details;

import android.os.Bundle;

import com.porto.isabel.popularmovies.model.moviedb.Movie;
import com.porto.isabel.popularmovies.model.moviedb.Video;
import com.porto.isabel.popularmovies.network.ReviewResult;
import com.porto.isabel.popularmovies.screens.details.domain.ScreenContent;

import rx.Observable;

public interface DetailsContract {

    interface PresenterContract {

        void onCreate(Bundle savedInstanceState);

        void onPlayTrailerClicked();

        void onShowReviewClicked();

        void onFavouriteClicked();

        void onCreateMenu();

        void onSaveInstanceState(Bundle outState);
    }

    interface ViewContract {

        void init(Movie movie);

        void showTrailerAndReview(String size);

        void showLoading();

        void setFavourite(boolean favourite);

        void showError();
    }

    interface InteractorContract {

        Movie getMovie();

        Observable<ScreenContent> loadScreenContent();

        ScreenContent getScreenContent();

        Video getTrailer();

        ReviewResult getReview();

        void setFavourite();

        boolean isFavourite();

        void setScreenContent(ScreenContent screenContent);
    }

    interface Router {

        void openYoutubeVideo(String id);

        void openReviewsScreen(ReviewResult review);
    }
}
