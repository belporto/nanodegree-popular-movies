package com.porto.isabel.popularmovies.details.di;

import com.porto.isabel.popularmovies.details.DetailsContract;
import com.porto.isabel.popularmovies.details.domain.DetailsInteractor;
import com.porto.isabel.popularmovies.details.presentation.DetailsActivity;
import com.porto.isabel.popularmovies.details.presentation.DetailsPresenter;
import com.porto.isabel.popularmovies.model.moviedb.Movie;

import dagger.Module;
import dagger.Provides;

/**
 * Created by belporto on 25/06/2017.
 */

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
    public DetailsContract.PresenterContract providePresenter(DetailsContract.ViewContract view,
            DetailsContract.InteractorContract interactor) {
        return new DetailsPresenter(view, interactor);
    }

    @Provides
    @DetailsScope
    public DetailsContract.InteractorContract provideInteractor() {
        return new DetailsInteractor(mMovie);
    }

}
