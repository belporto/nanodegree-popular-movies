package com.porto.isabel.popularmoviesstage1.movies;

import com.porto.isabel.popularmoviesstage1.model.moviedb.Movie;

import java.util.List;

import rx.Observable;

/**
 * Created by belporto on 11/06/2017.
 */

public interface MoviesContract {

    interface PresenterContract {

        void onCreate();

        void onDestroy();
    }

    interface ViewContract {

        void showMovies(List<Movie> movies);
    }

    interface InteractorContract {
        Observable<List<Movie>> getPopularMovies();
    }

}
