package com.porto.isabel.popularmoviesstage1.movies;

import com.porto.isabel.popularmoviesstage1.model.moviedb.Movie;
import com.porto.isabel.popularmoviesstage1.movies.domain.SortBy;

import java.util.List;

import rx.Observable;

/**
 * Created by belporto on 11/06/2017.
 */

public interface MoviesContract {

    interface PresenterContract {

        void onCreate();

        void onDestroy();

        void onSortByClicked();

        void onSortOptionClicked(SortBy sortBy);
    }

    interface ViewContract {

        void showMovies(List<Movie> movies);

        void showProgress();

        void showError();

        void showSortByOptions();
    }

    interface InteractorContract {
        Observable<List<Movie>> getMovies();

        void setSortBy(SortBy sortBy);

        SortBy getSortBy();
    }

}
