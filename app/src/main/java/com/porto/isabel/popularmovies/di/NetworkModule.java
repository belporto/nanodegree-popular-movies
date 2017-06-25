package com.porto.isabel.popularmovies.di;

import android.content.Context;

import com.porto.isabel.popularmovies.network.MovieDBApi;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    @Provides
    public MovieDBApi provideMovieDBApi(Context context) {
        return new MovieDBApi(context);
    }
}
