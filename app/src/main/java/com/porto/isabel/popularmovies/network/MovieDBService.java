package com.porto.isabel.popularmovies.network;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MovieDBService {

    @GET("movie/popular")
    Observable<MovieResult> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Observable<MovieResult> getTopRatedMovies(@Query("api_key") String apiKey);

}
