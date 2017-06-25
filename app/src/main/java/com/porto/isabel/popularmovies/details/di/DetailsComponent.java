package com.porto.isabel.popularmovies.details.di;

import com.porto.isabel.popularmovies.details.presentation.DetailsActivity;
import com.porto.isabel.popularmovies.di.AppComponent;

import dagger.Component;

/**
 * Created by belporto on 25/06/2017.
 */

@DetailsScope
@Component(modules = {DetailsModule.class},
        dependencies = {AppComponent.class})
public interface DetailsComponent {

    void inject(DetailsActivity activity);

}
