package com.porto.isabel.popularmoviesstage1.di;

import android.content.Context;

import com.porto.isabel.popularmoviesstage1.network.MovieDBApi;

import dagger.Component;

/**
 * Created by belporto on 11/06/2017.
 */

@AppScope
@Component(modules = {
        AppModule.class, NetworkModule.class
})
public interface AppComponent {

    Context context();

    MovieDBApi movieDBApi();
}
