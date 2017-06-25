package com.porto.isabel.popularmovies.details.presentation;

import com.porto.isabel.popularmovies.details.DetailsContract;
import com.porto.isabel.popularmovies.model.moviedb.Movie;

/**
 * Created by belporto on 25/06/2017.
 */

public class DetailsPresenter implements DetailsContract.PresenterContract {

    private final DetailsContract.ViewContract mView;
    private final DetailsContract.InteractorContract mInteractor;


    public DetailsPresenter(DetailsContract.ViewContract view, DetailsContract.InteractorContract interactor) {
        mView = view;
        mInteractor = interactor;
    }

    @Override
    public void onCreate() {
        Movie movie = mInteractor.getMovie();
        mView.init(movie);
    }


}
