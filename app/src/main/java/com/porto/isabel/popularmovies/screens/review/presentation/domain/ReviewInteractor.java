package com.porto.isabel.popularmovies.screens.review.presentation.domain;

import com.porto.isabel.popularmovies.model.moviedb.Review;
import com.porto.isabel.popularmovies.network.ReviewResult;
import com.porto.isabel.popularmovies.screens.review.ReviewContract;

import java.util.List;

public class ReviewInteractor implements ReviewContract.Interactor {

    private final ReviewResult mReviewResult;

    public ReviewInteractor(ReviewResult reviewResult) {
        mReviewResult = reviewResult;
    }

    @Override
    public List<Review> getReviews() {
        return mReviewResult.getResults();
    }
}
