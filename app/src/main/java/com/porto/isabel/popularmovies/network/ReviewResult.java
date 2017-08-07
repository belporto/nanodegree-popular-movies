package com.porto.isabel.popularmovies.network;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.porto.isabel.popularmovies.model.moviedb.Review;

import java.util.List;

public class ReviewResult implements Parcelable {

    @SerializedName("page")
    private Integer page;

    @SerializedName("total_results")
    private Integer totalResults;

    @SerializedName("total_pages")
    private Integer totalPages;

    @SerializedName("results")
    private List<Review> results;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.page);
        dest.writeValue(this.totalResults);
        dest.writeValue(this.totalPages);
        dest.writeTypedList(this.results);
    }

    public ReviewResult() {
    }

    protected ReviewResult(Parcel in) {
        this.page = (Integer) in.readValue(Integer.class.getClassLoader());
        this.totalResults = (Integer) in.readValue(Integer.class.getClassLoader());
        this.totalPages = (Integer) in.readValue(Integer.class.getClassLoader());
        this.results = in.createTypedArrayList(Review.CREATOR);
    }

    public static final Creator<ReviewResult> CREATOR = new Creator<ReviewResult>() {
        @Override
        public ReviewResult createFromParcel(Parcel source) {
            return new ReviewResult(source);
        }

        @Override
        public ReviewResult[] newArray(int size) {
            return new ReviewResult[size];
        }
    };
}
