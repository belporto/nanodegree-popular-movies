package com.porto.isabel.popularmoviesstage1.movies.di;

import com.porto.isabel.popularmoviesstage1.di.AppComponent;
import com.porto.isabel.popularmoviesstage1.movies.presentation.MoviesActivity;

import dagger.Component;

/**
 * Created by belporto on 11/06/2017.
 */

@MoviesScope
@Component(modules = {MoviesModule.class},
        dependencies = {AppComponent.class})
public interface MoviesComponent {

    void inject(MoviesActivity activity);

}
