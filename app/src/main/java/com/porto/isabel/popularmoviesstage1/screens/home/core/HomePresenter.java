package com.porto.isabel.popularmoviesstage1.screens.home.core;

import android.util.Log;

import com.porto.isabel.popularmoviesstage1.screens.home.HomeContract;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by belporto on 11/06/2017.
 */

public class HomePresenter implements HomeContract.PresenterContract {

    private final HomeContract.ViewContract mView;
    private final HomeContract.InteractorContract mInteractor;
    private CompositeSubscription compositeSubscription;


    public HomePresenter(HomeContract.ViewContract view, HomeContract.InteractorContract interactor) {
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
