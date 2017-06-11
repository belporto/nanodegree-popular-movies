package com.porto.isabel.popularmoviesstage1.network;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by belporto on 11/06/2017.
 */

public interface MovieDBService {

    @GET("movie/popular")
    Observable<MovieResult> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Observable<MovieResult> getTopRatedMovies(@Query("api_key") String apiKey);

}
