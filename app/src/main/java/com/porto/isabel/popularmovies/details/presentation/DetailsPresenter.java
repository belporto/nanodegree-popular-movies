package com.porto.isabel.popularmovies.details.presentation;

import com.porto.isabel.popularmovies.details.DetailsContract;
import com.porto.isabel.popularmovies.model.moviedb.Movie;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class DetailsPresenter implements DetailsContract.PresenterContract {

    private final DetailsContract.ViewContract mView;
    private final DetailsContract.InteractorContract mInteractor;
    private final DetailsContract.Router mRouter;
    private CompositeSubscription compositeSubscription;


    public DetailsPresenter(DetailsContract.ViewContract view, DetailsContract.InteractorContract interactor, DetailsContract.Router router) {
        mView = view;
        mInteractor = interactor;
        mRouter = router;
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onCreate() {
        compositeSubscription.add(subscribeGetVideos());
    }

    @Override
    public void onPlayTrailerClicked() {
        mRouter.openYoutubeVideo(mInteractor.getTrailer().getKey());
    }

    private Subscription subscribeGetVideos() {
        return mInteractor.getVideos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(mView::showLoading)
                .subscribe(videos -> {
                    Movie movie = mInteractor.getMovie();
                    mView.init(movie);
                });


    }


}
