package com.porto.isabel.popularmoviesstage1.screens.home.core;

import com.porto.isabel.popularmoviesstage1.dagger.AppComponent;
import com.porto.isabel.popularmoviesstage1.screens.home.HomeActivity;
import com.porto.isabel.popularmoviesstage1.screens.home.dagger.HomeModule;
import com.porto.isabel.popularmoviesstage1.screens.home.dagger.HomeScope;

import dagger.Component;

/**
 * Created by belporto on 11/06/2017.
 */

@HomeScope
@Component(modules = {HomeModule.class},
        dependencies = {AppComponent.class})
public interface HomeComponent {

    void inject(HomeActivity activity);

}
