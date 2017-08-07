package com.porto.isabel.popularmovies.di;

import android.content.Context;

import com.porto.isabel.popularmovies.network.MovieDBApi;
import com.porto.isabel.popularmovies.repository.FavouritesRepository;

import dagger.Component;

@AppScope
@Component(modules = {
        AppModule.class, NetworkModule.class, RepositoryModule.class
})
public interface AppComponent {

    Context context();

    MovieDBApi movieDBApi();

    FavouritesRepository favouriteRepository();
}
