package com.porto.isabel.popularmoviesstage1.dagger;

import android.content.Context;

import com.porto.isabel.popularmoviesstage1.network.MovieDBApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by belporto on 11/06/2017.
 */

@Module
public class NetworkModule {

    @Provides
    public MovieDBApi provideMovieDBApi(Context context) {
        return new MovieDBApi(context);
    }
}
