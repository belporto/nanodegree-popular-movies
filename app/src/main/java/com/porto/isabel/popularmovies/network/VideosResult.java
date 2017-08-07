package com.porto.isabel.popularmovies.network;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.porto.isabel.popularmovies.model.moviedb.Video;

import java.util.List;

public class VideosResult implements Parcelable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("results")
    private List<Video> results;

    public Integer getId() {
        return id;
    }

    public List<Video> getResults() {
        return results;
    }

    protected VideosResult(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VideosResult> CREATOR = new Creator<VideosResult>() {
        @Override
        public VideosResult createFromParcel(Parcel in) {
            return new VideosResult(in);
        }

        @Override
        public VideosResult[] newArray(int size) {
            return new VideosResult[size];
        }
    };
}
