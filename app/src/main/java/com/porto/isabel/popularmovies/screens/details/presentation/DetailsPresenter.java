package com.porto.isabel.popularmovies.screens.details.presentation;

import android.os.Bundle;

import com.porto.isabel.popularmovies.screens.details.DetailsContract;
import com.porto.isabel.popularmovies.screens.details.domain.ScreenContent;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class DetailsPresenter implements DetailsContract.PresenterContract {

    private static final String BUNDLE_SCREEN_CONTENT = "BUNDLE_SCREEN_CONTENT";
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
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            ScreenContent screenContent = savedInstanceState.getParcelable(BUNDLE_SCREEN_CONTENT);
            mInteractor.setScreenContent(screenContent);
        }

        mView.init(mInteractor.getMovie());
        compositeSubscription.add(subscribeGetScreenContent());
    }

    @Override
    public void onPlayTrailerClicked() {
        mRouter.openYoutubeVideo(mInteractor.getTrailer().getKey());
    }

    @Override
    public void onShowReviewClicked() {

        mRouter.openReviewsScreen(mInteractor.getReview());
    }

    @Override
    public void onFavouriteClicked() {
        mInteractor.setFavourite();
        mView.setFavourite(mInteractor.isFavourite());
    }

    @Override
    public void onCreateMenu() {
        mView.setFavourite(mInteractor.isFavourite());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(BUNDLE_SCREEN_CONTENT, mInteractor.getScreenContent());
    }

    private Subscription subscribeGetScreenContent() {
        return mInteractor.loadScreenContent()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(mView::showLoading)
                .subscribe(screenContent -> mView.showTrailerAndReview(screenContent.getReviewResult()
                                .getTotalResults().toString()),
                        throwable -> mView.showError());


    }


}
