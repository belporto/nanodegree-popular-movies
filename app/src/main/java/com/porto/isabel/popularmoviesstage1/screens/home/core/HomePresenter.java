package com.porto.isabel.popularmoviesstage1.screens.home.core;

import com.porto.isabel.popularmoviesstage1.screens.home.HomeContract;

/**
 * Created by belporto on 11/06/2017.
 */

public class HomePresenter implements HomeContract.PresenterContract {

    private final HomeContract.ViewContract mView;
    private final HomeContract.InteractorContract mInteractor;

    public HomePresenter(HomeContract.ViewContract view, HomeContract.InteractorContract interactor) {
        mView = view;
        mInteractor = interactor;
    }

    @Override
    public void onCreate() {

    }
}
