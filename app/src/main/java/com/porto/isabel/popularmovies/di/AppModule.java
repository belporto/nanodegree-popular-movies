package com.porto.isabel.popularmovies.di;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by belporto on 11/06/2017.
 */

@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    public Context provideContext() {
        return mApplication;
    }
}
