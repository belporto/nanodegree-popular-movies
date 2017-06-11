package com.porto.isabel.popularmoviesstage1.network;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by belporto on 11/06/2017.
 */

public class MovieDBApi {

    private MovieDBService mService;
    private Context mContext;


    public MovieDBApi(Context mContext) {
        this.mContext = mContext;

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://api.themoviedb.org/3/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = retrofit.create(MovieDBService.class);
    }

    public Observable<MovieResult> getPopularMovies() {
        return mService.getPopularMovies(getApiKey());
    }

    public String getApiKey() {
        String apiKey = "";
        try {
            ApplicationInfo ai = mContext.getPackageManager().getApplicationInfo(
                    mContext.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            apiKey = bundle.getString("MOVIES_DB_KEY");
        } catch (Exception e) {
            //TODO:
        }

        return apiKey;
    }

}
