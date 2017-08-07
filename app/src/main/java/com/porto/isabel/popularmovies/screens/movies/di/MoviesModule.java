package com.porto.isabel.popularmovies.screens.movies.di;

import com.porto.isabel.popularmovies.screens.movies.MoviesContract;
import com.porto.isabel.popularmovies.screens.movies.domain.MoviesInteractor;
import com.porto.isabel.popularmovies.screens.movies.presentation.MoviesActivity;
import com.porto.isabel.popularmovies.screens.movies.presentation.MoviesPresenter;
import com.porto.isabel.popularmovies.screens.movies.presentation.MoviesRouter;
import com.porto.isabel.popularmovies.network.MovieDBApi;

import dagger.Module;
import dagger.Provides;

@MoviesScope
@Module
public class MoviesModule {

    private final MoviesActivity mActivity;

    public MoviesModule(MoviesActivity activity) {
        mActivity = activity;
    }

    @Provides
    @MoviesScope
    public MoviesContract.View provideView() {
        return mActivity;
    }

    @Provides
    @MoviesScope
    public MoviesContract.Presenter providePresenter(MoviesContract.View view,
                                                     MoviesContract.Interactor interactor, MoviesContract.Router router) {
        return new MoviesPresenter(view, interactor, router);
    }

    @Provides
    @MoviesScope
    public MoviesContract.Interactor provideInteractor(MovieDBApi movieDBApi) {
        return new MoviesInteractor(movieDBApi);
    }

    @Provides
    @MoviesScope
    public MoviesContract.Router provideRouter() {
        return new MoviesRouter(mActivity);
    }

}
