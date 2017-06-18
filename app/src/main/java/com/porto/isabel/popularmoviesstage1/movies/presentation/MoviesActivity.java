package com.porto.isabel.popularmoviesstage1.movies.presentation;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.porto.isabel.popularmoviesstage1.AppApplication;
import com.porto.isabel.popularmoviesstage1.R;
import com.porto.isabel.popularmoviesstage1.di.AppComponent;
import com.porto.isabel.popularmoviesstage1.model.moviedb.Movie;
import com.porto.isabel.popularmoviesstage1.movies.MoviesContract;
import com.porto.isabel.popularmoviesstage1.movies.di.DaggerMoviesComponent;
import com.porto.isabel.popularmoviesstage1.movies.di.MoviesModule;
import com.porto.isabel.popularmoviesstage1.movies.domain.SortBy;
import com.porto.isabel.popularmoviesstage1.movies.presentation.adapter.MoviesAdapter;

import java.util.List;

import javax.inject.Inject;

public class MoviesActivity extends AppCompatActivity implements MoviesContract.ViewContract {

    private static final int NUMBER_OF_COLUMNS = 3;
    @Inject
    MoviesContract.PresenterContract mPresenter;

    private RecyclerView mRecyclerView;
    private MoviesAdapter mMoviesAdapter;
    private View mErrorView;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppComponent appComponent = ((AppApplication) getApplication()).getAppComponent();
        DaggerMoviesComponent.builder().appComponent(appComponent).moviesModule(
                new MoviesModule(this)).build().inject(this);

        setContentView(R.layout.activity_movies);
        mPresenter.onCreate();

        //TODO: View injection
        mRecyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        mProgressBar = (ProgressBar) findViewById(R.id.movies_loading);
        mErrorView = findViewById(R.id.movies_error);

        GridLayoutManager layoutManager
                = new GridLayoutManager(this, NUMBER_OF_COLUMNS);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mMoviesAdapter = new MoviesAdapter();
        mRecyclerView.setAdapter(mMoviesAdapter);

    }


    @Override
    public void showMovies(List<Movie> movies) {
        mMoviesAdapter.setData(movies);
        mProgressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSortByOptions() {
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = getLayoutInflater().inflate(R.layout.sort_by, null);

        //TODO: show check
        View mostPopular = sheetView.findViewById(R.id.most_popular);
        View topReated = sheetView.findViewById(R.id.top_rated);

        mostPopular.setOnClickListener(v -> mPresenter.onSortOptionClicked(SortBy.MOST_POPULAR));

        topReated.setOnClickListener(v -> mPresenter.onSortOptionClicked(SortBy.TOP_RATED));
        mBottomSheetDialog.setContentView(sheetView);
        mBottomSheetDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sort_by) {
            mPresenter.onSortByClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
