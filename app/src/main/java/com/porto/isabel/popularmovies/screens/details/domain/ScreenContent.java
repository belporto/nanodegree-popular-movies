package com.porto.isabel.popularmovies.screens.details.domain;


import android.os.Parcel;
import android.os.Parcelable;

import com.porto.isabel.popularmovies.network.ReviewResult;
import com.porto.isabel.popularmovies.network.VideosResult;

public class ScreenContent implements Parcelable {

    private VideosResult mVideosResult;
    private ReviewResult mReviewResult;

    public ScreenContent(VideosResult videosResult, ReviewResult reviewResult) {
        this.mVideosResult = videosResult;
        this.mReviewResult = reviewResult;
    }

    public ReviewResult getReviewResult() {
        return mReviewResult;
    }

    public VideosResult getVideosResult() {
        return mVideosResult;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mVideosResult, flags);
        dest.writeParcelable(this.mReviewResult, flags);
    }

    protected ScreenContent(Parcel in) {
        this.mVideosResult = in.readParcelable(VideosResult.class.getClassLoader());
        this.mReviewResult = in.readParcelable(ReviewResult.class.getClassLoader());
    }

    public static final Creator<ScreenContent> CREATOR = new Creator<ScreenContent>() {
        @Override
        public ScreenContent createFromParcel(Parcel source) {
            return new ScreenContent(source);
        }

        @Override
        public ScreenContent[] newArray(int size) {
            return new ScreenContent[size];
        }
    };
}
