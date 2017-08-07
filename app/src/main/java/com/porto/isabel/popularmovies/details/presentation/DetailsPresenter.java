package com.porto.isabel.popularmovies.details.presentation;

import android.util.Log;

import com.porto.isabel.popularmovies.details.DetailsContract;
import com.porto.isabel.popularmovies.model.moviedb.Movie;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class DetailsPresenter implements DetailsContract.PresenterContract {

    private final DetailsContract.ViewContract mView;
    private final DetailsContract.InteractorContract mInteractor;
    private CompositeSubscription compositeSubscription;


    public DetailsPresenter(DetailsContract.ViewContract view, DetailsContract.InteractorContract interactor) {
        mView = view;
        mInteractor = interactor;
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onCreate() {
        Movie movie = mInteractor.getMovie();
        mView.init(movie);
        compositeSubscription.add(subscribeGetVideos());
    }

    private Subscription subscribeGetVideos() {
        return mInteractor.getVideos().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(videos -> {
            Log.d("TAG", "videos - " + videos);
        });
    }


}
