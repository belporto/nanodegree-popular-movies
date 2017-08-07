package com.porto.isabel.popularmovies.details.presentation;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.porto.isabel.popularmovies.details.DetailsContract;

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
}
