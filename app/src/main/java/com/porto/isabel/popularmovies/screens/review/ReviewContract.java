package com.porto.isabel.popularmovies.screens.review;

import com.porto.isabel.popularmovies.model.moviedb.Review;

import java.util.List;

public interface ReviewContract {

    interface Presenter {
        void onCreate();
    }

    interface View {

        void init(List<Review> reviews);
    }

    interface Interactor {

        List<Review> getReviews();
    }

}
