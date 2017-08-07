package com.porto.isabel.popularmovies.di;

import android.content.Context;

import com.porto.isabel.popularmovies.repository.ContentResolverFavouritesRepository;
import com.porto.isabel.popularmovies.repository.FavouritesRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    public FavouritesRepository provideFavouriteRepository(Context context) {
        return new ContentResolverFavouritesRepository(context);
    }
}
