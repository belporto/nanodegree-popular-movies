package com.porto.isabel.popularmovies.movies.presentation;

import android.os.Bundle;
import android.util.Log;

import com.porto.isabel.popularmovies.model.moviedb.Movie;
import com.porto.isabel.popularmovies.movies.MoviesContract;
import com.porto.isabel.popularmovies.movies.domain.SortBy;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MoviesPresenter implements MoviesContract.PresenterContract {

    private static final String TAG = MoviesPresenter.class.getSimpleName();
    private static final String BUNDLE_SORT_BY = "BUNDLE_SORT_BY";
    private final MoviesContract.ViewContract mView;
    private final MoviesContract.InteractorContract mInteractor;
    private final MoviesContract.RouterContract mRouter;
    private CompositeSubscription compositeSubscription;


    public MoviesPresenter(MoviesContract.ViewContract view, MoviesContract.InteractorContract interactor, MoviesContract.RouterContract router) {
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
        }
        compositeSubscription.add(subscribeGetPopularMovies(false));
    }

    private Subscription subscribeGetPopularMovies(boolean onRefresh) {
        return Observable.just(null)
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnNext(aVoid -> {
                    if (!onRefresh) {
                        mView.showProgress();
                    }
                })
                .observeOn(Schedulers.io())
                .switchMap(aVoid -> mInteractor.getMovies())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mView::showMovies,
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
        if (mInteractor.getSortBy() != sortBy) {
            mInteractor.setSortBy(sortBy);
            compositeSubscription.add(subscribeGetPopularMovies(false));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(BUNDLE_SORT_BY, mInteractor.getSortBy());
    }

    @Override
    public void onMovieClicked(Movie movie) {
        mRouter.showDetails(movie);
    }

    @Override
    public void onRefresh() {
        compositeSubscription.add(subscribeGetPopularMovies(true));
    }
}
