package com.porto.isabel.popularmovies.screens.review.di;

import com.porto.isabel.popularmovies.di.AppComponent;
import com.porto.isabel.popularmovies.screens.review.presentation.ReviewActivity;

import dagger.Component;

@ReviewScope
@Component(modules = {ReviewModule.class},
        dependencies = {AppComponent.class})
public interface ReviewComponent {

    void inject(ReviewActivity activity);

}
