package com.porto.isabel.popularmoviesstage1.movies.di;

import com.porto.isabel.popularmoviesstage1.movies.MoviesContract;
import com.porto.isabel.popularmoviesstage1.movies.domain.MoviesInteractor;
import com.porto.isabel.popularmoviesstage1.movies.presentation.MoviesActivity;
import com.porto.isabel.popularmoviesstage1.movies.presentation.MoviesPresenter;
import com.porto.isabel.popularmoviesstage1.network.MovieDBApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by belporto on 11/06/2017.
 */

@MoviesScope
@Module
public class MoviesModule {

    private final MoviesActivity mActivity;

    public MoviesModule(MoviesActivity activity) {
        mActivity = activity;
    }

    @Provides
    @MoviesScope
    public MoviesContract.ViewContract provideView() {
        return mActivity;
    }

    @Provides
    @MoviesScope
    public MoviesContract.PresenterContract providePresenter(MoviesContract.ViewContract view,
            MoviesContract.InteractorContract interactor) {
        return new MoviesPresenter(view, interactor);
    }

    @Provides
    @MoviesScope
    public MoviesContract.InteractorContract provideInteractor(MovieDBApi movieDBApi) {
        return new MoviesInteractor(movieDBApi);
    }
}
