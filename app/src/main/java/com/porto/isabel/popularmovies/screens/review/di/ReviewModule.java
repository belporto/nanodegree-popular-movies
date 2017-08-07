package com.porto.isabel.popularmovies.screens.review.di;

import com.porto.isabel.popularmovies.network.ReviewResult;
import com.porto.isabel.popularmovies.screens.review.ReviewContract;
import com.porto.isabel.popularmovies.screens.review.presentation.ReviewActivity;
import com.porto.isabel.popularmovies.screens.review.presentation.ReviewPresenter;
import com.porto.isabel.popularmovies.screens.review.presentation.domain.ReviewInteractor;

import dagger.Module;
import dagger.Provides;

@ReviewScope
@Module
public class ReviewModule {

    private final ReviewActivity mActivity;
    private final ReviewResult mReviewResult;

    public ReviewModule(ReviewActivity activity, ReviewResult reviewResult) {
        mActivity = activity;
        mReviewResult = reviewResult;
    }

    @Provides
    @ReviewScope
    public ReviewContract.View provideView() {
        return mActivity;
    }

    @Provides
    @ReviewScope
    public ReviewContract.Presenter providePresenter(ReviewContract.View view,
                                                     ReviewContract.Interactor interactor) {
        return new ReviewPresenter(view, interactor);
    }

    @Provides
    @ReviewScope
    public ReviewContract.Interactor provideInteractor() {
        return new ReviewInteractor(mReviewResult);
    }

}
