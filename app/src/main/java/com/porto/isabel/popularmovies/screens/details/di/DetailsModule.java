package com.porto.isabel.popularmovies.screens.details.di;

import com.porto.isabel.popularmovies.model.moviedb.Movie;
import com.porto.isabel.popularmovies.network.MovieDBApi;
import com.porto.isabel.popularmovies.repository.FavouritesRepository;
import com.porto.isabel.popularmovies.screens.details.DetailsContract;
import com.porto.isabel.popularmovies.screens.details.domain.DetailsInteractor;
import com.porto.isabel.popularmovies.screens.details.presentation.DetailsActivity;
import com.porto.isabel.popularmovies.screens.details.presentation.DetailsPresenter;
import com.porto.isabel.popularmovies.screens.details.presentation.DetailsRouter;

import dagger.Module;
import dagger.Provides;

@DetailsScope
@Module
public class DetailsModule {

    private final DetailsActivity mActivity;
    private final Movie mMovie;

    public DetailsModule(DetailsActivity activity, Movie movie) {
        mActivity = activity;
        mMovie = movie;
    }

    @Provides
    @DetailsScope
    public DetailsContract.ViewContract provideView() {
        return mActivity;
    }

    @Provides
    @DetailsScope
    public DetailsContract.Router provideRouter() {
        return new DetailsRouter(mActivity);
    }

    @Provides
    @DetailsScope
    public DetailsContract.PresenterContract providePresenter(DetailsContract.ViewContract view,
                                                              DetailsContract.InteractorContract interactor, DetailsContract.Router router) {
        return new DetailsPresenter(view, interactor, router);
    }

    @Provides
    @DetailsScope
    public DetailsContract.InteractorContract provideInteractor(MovieDBApi movieDBApi, FavouritesRepository favouritesRepository) {
        return new DetailsInteractor(movieDBApi, mMovie, favouritesRepository);
    }

}
