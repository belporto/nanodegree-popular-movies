package com.porto.isabel.popularmovies.repository;


import com.porto.isabel.popularmovies.model.moviedb.Movie;

import java.util.List;

public interface FavouritesRepository {

    List<Movie> getFavourites();

    void addFavourite(Movie movie);

    void deleteFavourite(Movie movie);

    boolean isFavourite(Movie movie);

}
