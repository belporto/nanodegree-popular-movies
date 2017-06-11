package com.porto.isabel.popularmoviesstage1.screens.home.dagger;

import com.porto.isabel.popularmoviesstage1.network.MovieDBApi;
import com.porto.isabel.popularmoviesstage1.screens.home.HomeActivity;
import com.porto.isabel.popularmoviesstage1.screens.home.HomeContract;
import com.porto.isabel.popularmoviesstage1.screens.home.core.HomeInteractor;
import com.porto.isabel.popularmoviesstage1.screens.home.core.HomePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by belporto on 11/06/2017.
 */

@HomeScope
@Module
public class HomeModule {

    private final HomeActivity mActivity;

    public HomeModule(HomeActivity activity) {
        mActivity = activity;
    }

    @Provides
    @HomeScope
    public HomeContract.ViewContract provideView() {
        return mActivity;
    }

    @Provides
    @HomeScope
    public HomeContract.PresenterContract providePresenter(HomeContract.ViewContract view,
                                                           HomeContract.InteractorContract interactor) {
        return new HomePresenter(view, interactor);
    }

    @Provides
    @HomeScope
    public HomeContract.InteractorContract provideInteractor(MovieDBApi movieDBApi) {
        return new HomeInteractor(movieDBApi);
    }
}
