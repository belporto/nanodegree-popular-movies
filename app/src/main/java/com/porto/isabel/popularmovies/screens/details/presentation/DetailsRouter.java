package com.porto.isabel.popularmovies.screens.details.presentation;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.porto.isabel.popularmovies.network.ReviewResult;
import com.porto.isabel.popularmovies.screens.details.DetailsContract;
import com.porto.isabel.popularmovies.screens.review.presentation.ReviewActivity;

public class DetailsRouter implements DetailsContract.Router {

    private final Activity mActivity;

    public DetailsRouter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void openYoutubeVideo(String id) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + id));

        if (intent.resolveActivity(mActivity.getPackageManager()) != null) {
            mActivity.startActivity(intent);
        }
    }

    @Override
    public void openReviewsScreen(ReviewResult review) {
        Intent intent = new Intent(mActivity, ReviewActivity.class);
        intent.putExtra(ReviewActivity.EXTRA_REVIEW_RESULT, review);
        mActivity.startActivity(intent);
    }

}
