package com.porto.isabel.popularmovies.screens.review.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.porto.isabel.popularmovies.AppApplication;
import com.porto.isabel.popularmovies.R;
import com.porto.isabel.popularmovies.di.AppComponent;
import com.porto.isabel.popularmovies.model.moviedb.Review;
import com.porto.isabel.popularmovies.network.ReviewResult;
import com.porto.isabel.popularmovies.screens.review.ReviewContract;
import com.porto.isabel.popularmovies.screens.review.di.DaggerReviewComponent;
import com.porto.isabel.popularmovies.screens.review.di.ReviewModule;
import com.porto.isabel.popularmovies.screens.review.presentation.adapter.ReviewAdapter;

import java.util.List;

import javax.inject.Inject;

public class ReviewActivity extends AppCompatActivity implements ReviewContract.View {

    public static final String EXTRA_REVIEW_RESULT = "com.porto.isabel.popularmovies.EXTRA_REVIEW_RESULT";
    private RecyclerView mRecyclerView;
    private ReviewAdapter mReviewAdapter;

    @Inject
    ReviewContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_review);

        ReviewResult reviewResult = getIntent().getParcelableExtra(EXTRA_REVIEW_RESULT);

        AppComponent appComponent = ((AppApplication) getApplication()).getAppComponent();
        DaggerReviewComponent.builder().appComponent(appComponent).reviewModule(new ReviewModule(this, reviewResult)).build().inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.review_toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.review_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mReviewAdapter = new ReviewAdapter();
        mRecyclerView.setAdapter(mReviewAdapter);

        mPresenter.onCreate();
    }

    @Override
    public void init(List<Review> reviews) {
        mReviewAdapter.setData(reviews);
    }
}
