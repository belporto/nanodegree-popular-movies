package com.porto.isabel.popularmovies.details;

import com.porto.isabel.popularmovies.model.moviedb.Movie;

/**
 * Created by belporto on 11/06/2017.
 */

public interface DetailsContract {

    interface PresenterContract {

        void onCreate();
    }

    interface ViewContract {

        void init(Movie movie);
    }

    interface InteractorContract {

        Movie getMovie();
    }
}
