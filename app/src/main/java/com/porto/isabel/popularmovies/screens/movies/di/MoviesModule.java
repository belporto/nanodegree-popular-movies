package com.porto.isabel.popularmovies.screens.movies.di;

import com.porto.isabel.popularmovies.network.MovieDBApi;
import com.porto.isabel.popularmovies.repository.FavouritesRepository;
import com.porto.isabel.popularmovies.screens.movies.MoviesContract;
import com.porto.isabel.popularmovies.screens.movies.domain.MoviesInteractor;
import com.porto.isabel.popularmovies.screens.movies.presentation.MoviesActivity;
import com.porto.isabel.popularmovies.screens.movies.presentation.MoviesPresenter;
import com.porto.isabel.popularmovies.screens.movies.presentation.MoviesRouter;

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
    public MoviesContract.Interactor provideInteractor(MovieDBApi movieDBApi, FavouritesRepository favouritesRepository) {
        return new MoviesInteractor(movieDBApi, favouritesRepository);
    }

    @Provides
    @MoviesScope
    public MoviesContract.Router provideRouter() {
        return new MoviesRouter(mActivity);
    }

}
