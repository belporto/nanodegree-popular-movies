package com.porto.isabel.popularmoviesstage1.movies.presentation;

import android.util.Log;

import com.porto.isabel.popularmoviesstage1.movies.MoviesContract;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by belporto on 11/06/2017.
 */

public class MoviesPresenter implements MoviesContract.PresenterContract {

    private final MoviesContract.ViewContract mView;
    private final MoviesContract.InteractorContract mInteractor;
    private CompositeSubscription compositeSubscription;


    public MoviesPresenter(MoviesContract.ViewContract view, MoviesContract.InteractorContract interactor) {
        mView = view;
        mInteractor = interactor;
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onCreate() {
        compositeSubscription.add(subscribeGetPopularMovies());
    }

    private Subscription subscribeGetPopularMovies() {
        return mInteractor.getPopularMovies().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mView::showMovies,
                        throwable -> Log.e("TAG", "", throwable));
    }

    @Override
    public void onDestroy() {
        compositeSubscription.clear();
    }
}
