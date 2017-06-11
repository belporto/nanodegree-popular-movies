package com.porto.isabel.popularmoviesstage1.dagger;

import android.content.Context;

import dagger.Component;

/**
 * Created by belporto on 11/06/2017.
 */

@AppScope
@Component(modules = {
        AppModule.class
})
public interface AppComponent {

    Context context();
}
