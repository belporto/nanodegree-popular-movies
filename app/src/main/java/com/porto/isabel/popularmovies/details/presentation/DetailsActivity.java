package com.porto.isabel.popularmovies.details.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.porto.isabel.popularmovies.R;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "com.porto.isabel.popularmovies.EXTRA_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

}
