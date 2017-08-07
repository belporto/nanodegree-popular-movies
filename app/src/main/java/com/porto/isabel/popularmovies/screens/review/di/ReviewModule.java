package com.porto.isabel.popularmovies.screens.review.di;

import com.porto.isabel.popularmovies.screens.review.ReviewContract;
import com.porto.isabel.popularmovies.screens.review.presentation.ReviewActivity;

import dagger.Module;
import dagger.Provides;

@ReviewScope
@Module
public class ReviewModule {

    private final ReviewActivity mActivity;

    public ReviewModule(ReviewActivity activity) {
        mActivity = activity;
    }

    @Provides
    @ReviewScope
    public ReviewContract.View provideView() {
        return mActivity;
    }

}
