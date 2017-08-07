package com.porto.isabel.popularmovies.screens.movies;

import android.os.Bundle;

import com.porto.isabel.popularmovies.model.moviedb.Movie;
import com.porto.isabel.popularmovies.screens.movies.domain.SortBy;

import java.util.List;

import rx.Observable;

public interface MoviesContract {

    interface Presenter {

        void onCreate(Bundle savedInstanceState);

        void onDestroy();

        void onSortByClicked();

        void onSortOptionClicked(SortBy sortBy);

        void onSaveInstanceState(Bundle outState);

        void onMovieClicked(Movie movie);

        void onRefresh();
    }

    interface View {

        void showMovies(List<Movie> movies);

        void showProgress();

        void showError();

        void showSortByOptions(SortBy sortBy);
    }

    interface Interactor {
        Observable<List<Movie>> getMovies();

        void setSortBy(SortBy sortBy);

        SortBy getSortBy();
    }

    interface Router {
        void showDetails(Movie movie);
    }
}
