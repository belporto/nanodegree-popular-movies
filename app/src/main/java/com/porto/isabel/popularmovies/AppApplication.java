package com.porto.isabel.popularmovies;

import android.app.Application;

import com.porto.isabel.popularmovies.di.AppComponent;
import com.porto.isabel.popularmovies.di.AppModule;
import com.porto.isabel.popularmovies.di.DaggerAppComponent;

/**
 * Created by belporto on 11/06/2017.
 */

public class AppApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = initDagger(this);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    protected AppComponent initDagger(AppApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }

}
