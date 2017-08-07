package com.porto.isabel.popularmovies.screens.movies.di;

import com.porto.isabel.popularmovies.di.AppComponent;
import com.porto.isabel.popularmovies.screens.movies.presentation.MoviesActivity;

import dagger.Component;

@MoviesScope
@Component(modules = {MoviesModule.class},
        dependencies = {AppComponent.class})
public interface MoviesComponent {

    void inject(MoviesActivity activity);

}
