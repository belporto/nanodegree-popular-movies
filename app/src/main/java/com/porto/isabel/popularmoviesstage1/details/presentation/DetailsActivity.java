package com.porto.isabel.popularmoviesstage1.details.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.porto.isabel.popularmoviesstage1.R;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "com.porto.isabel.popularmoviesstage1.EXTRA_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

}
