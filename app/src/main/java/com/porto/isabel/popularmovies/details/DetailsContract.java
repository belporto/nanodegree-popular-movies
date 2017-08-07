package com.porto.isabel.popularmovies.details;

import com.porto.isabel.popularmovies.model.moviedb.Movie;
import com.porto.isabel.popularmovies.model.moviedb.Video;

import java.util.List;

import rx.Observable;

public interface DetailsContract {

    interface PresenterContract {

        void onCreate();

        void onPlayTrailerClicked();
    }

    interface ViewContract {

        void init(Movie movie);

        void showLoading();
    }

    interface InteractorContract {

        Movie getMovie();

        Observable<List<Video>> getVideos();

        Video getTrailer();
    }

    interface Router {

        void openYoutubeVideo(String id);
    }
}
