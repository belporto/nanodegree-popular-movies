package com.porto.isabel.popularmovies.screens.review.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.porto.isabel.popularmovies.R;
import com.porto.isabel.popularmovies.screens.review.ReviewContract;

public class ReviewActivity extends AppCompatActivity implements ReviewContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_review);
    }
}
