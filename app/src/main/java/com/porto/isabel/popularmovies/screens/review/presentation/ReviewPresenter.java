package com.porto.isabel.popularmovies.screens.review.presentation;

import com.porto.isabel.popularmovies.screens.review.ReviewContract;

public class ReviewPresenter implements ReviewContract.Presenter {

    private final ReviewContract.View mView;
    private final ReviewContract.Interactor mInteractor;


    public ReviewPresenter(ReviewContract.View view, ReviewContract.Interactor interactor) {
        mView = view;
        mInteractor = interactor;
    }

    @Override
    public void onCreate() {
        mView.init(mInteractor.getReviews());
    }
}
