package com.porto.isabel.popularmovies.movies.di;

import com.porto.isabel.popularmovies.movies.MoviesContract;
import com.porto.isabel.popularmovies.movies.domain.MoviesInteractor;
import com.porto.isabel.popularmovies.movies.presentation.MoviesActivity;
import com.porto.isabel.popularmovies.movies.presentation.MoviesPresenter;
import com.porto.isabel.popularmovies.movies.presentation.MoviesRouter;
import com.porto.isabel.popularmovies.network.MovieDBApi;

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
            MoviesContract.InteractorContract interactor, MoviesContract.RouterContract router) {
        return new MoviesPresenter(view, interactor, router);
    }

    @Provides
    @MoviesScope
    public MoviesContract.InteractorContract provideInteractor(MovieDBApi movieDBApi) {
        return new MoviesInteractor(movieDBApi);
    }

    @Provides
    @MoviesScope
    public MoviesContract.RouterContract provideRouter() {
        return new MoviesRouter(mActivity);
    }

}
