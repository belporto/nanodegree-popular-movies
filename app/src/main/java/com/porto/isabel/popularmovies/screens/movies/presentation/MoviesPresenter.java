package com.porto.isabel.popularmovies.screens.movies.presentation;

import android.os.Bundle;
import android.util.Log;

import com.porto.isabel.popularmovies.model.moviedb.Movie;
import com.porto.isabel.popularmovies.screens.movies.MoviesContract;
import com.porto.isabel.popularmovies.screens.movies.domain.SortBy;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MoviesPresenter implements MoviesContract.Presenter {

    private static final String TAG = MoviesPresenter.class.getSimpleName();
    private static final String BUNDLE_SORT_BY = "BUNDLE_SORT_BY";
    private static final String BUNDLE_MOVIES = "BUNDLE_MOVIES";
    private final MoviesContract.View mView;
    private final MoviesContract.Interactor mInteractor;
    private final MoviesContract.Router mRouter;
    private CompositeSubscription compositeSubscription;


    public MoviesPresenter(MoviesContract.View view, MoviesContract.Interactor interactor, MoviesContract.Router router) {
        mView = view;
        mInteractor = interactor;
        mRouter = router;
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            SortBy sortBy = (SortBy) savedInstanceState.get(BUNDLE_SORT_BY);
            if (sortBy != null) {
                mInteractor.setSortBy(sortBy);
            }
            List<Movie> movies = savedInstanceState.getParcelableArrayList(BUNDLE_MOVIES);
            mInteractor.setMovies(movies);
        }
        compositeSubscription.add(subscribeGetPopularMovies(mInteractor.getSortBy(), false));
    }

    private Subscription subscribeGetPopularMovies(SortBy sortBy, boolean onRefresh) {
        return Observable.just(null)
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnNext(aVoid -> {
                    if (!onRefresh) {
                        mView.showProgress();
                    }
                })
                .observeOn(Schedulers.io())
                .switchMap(aVoid -> mInteractor.getMovies(sortBy))
                .doOnNext(mInteractor::setMovies)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> {
                            if (movies.isEmpty()) {
                                mView.showEmptyState();
                            } else {
                                mView.showMovies(movies);
                            }
                        },
                        throwable -> {
                            Log.e(TAG, "Error getting movies", throwable);
                            mView.showError();
                        }

                );
    }

    public void onDestroy() {
        compositeSubscription.clear();
    }

    @Override
    public void onSortByClicked() {
        mView.showSortByOptions(mInteractor.getSortBy());
    }

    @Override
    public void onSortOptionClicked(SortBy sortBy) {
        compositeSubscription.add(subscribeGetPopularMovies(sortBy, false));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(BUNDLE_SORT_BY, mInteractor.getSortBy());
        if (mInteractor.getMovies() != null) {
            outState.putSerializable(BUNDLE_MOVIES, new ArrayList<>(mInteractor.getMovies()));
        }
    }

    @Override
    public void onMovieClicked(Movie movie) {
        mRouter.showDetails(movie);
    }

    @Override
    public void onRefresh() {
        compositeSubscription.add(subscribeGetPopularMovies(mInteractor.getSortBy(), true));
    }
}
