package com.porto.isabel.popularmovies.repository;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.porto.isabel.popularmovies.model.moviedb.Movie;

import java.util.ArrayList;
import java.util.List;

public class ContentResolverFavouritesRepository implements FavouritesRepository {

    private Context mContext;

    public ContentResolverFavouritesRepository(Context activity) {
        this.mContext = activity;
    }

    @Override
    public List<Movie> getFavourites() {
        List<Movie> movies = new ArrayList<>();
        Cursor cursor = mContext.getContentResolver()
                .query(MoviesContract.FavouritesEntry.CONTENT_URI, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();
                movie.setOverview(cursor.getString(cursor.getColumnIndex(MoviesContract.FavouritesEntry.COLUMN_OVERVIEW)));
                movie.setTitle(cursor.getString(cursor.getColumnIndex(MoviesContract.FavouritesEntry.COLUMN_TITLE)));
                movie.setId(cursor.getInt(cursor.getColumnIndex(MoviesContract.FavouritesEntry._ID)));
                movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(MoviesContract.FavouritesEntry.COLUMN_RELEASE_DATE)));
                movie.setVoteAverage(cursor.getDouble(cursor.getColumnIndex(MoviesContract.FavouritesEntry.COLUMN_VOTE_AVERAGE)));
                movie.setPosterPath(cursor.getString(cursor.getColumnIndex(MoviesContract.FavouritesEntry.COLUMN_POSTER_PATH)));
                movie.setBackdropPath(cursor.getString(cursor.getColumnIndex(MoviesContract.FavouritesEntry.COLUMN_BACKDROP_PATH)));

                movies.add(movie);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return movies;
    }


    @Override
    public void addFavourite(Movie movie) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MoviesContract.FavouritesEntry._ID, movie.getId());
        contentValues.put(MoviesContract.FavouritesEntry.COLUMN_OVERVIEW, movie.getOverview());
        contentValues.put(MoviesContract.FavouritesEntry.COLUMN_RELEASE_DATE, movie.getReleaseDate());
        contentValues.put(MoviesContract.FavouritesEntry.COLUMN_TITLE, movie.getTitle());
        contentValues.put(MoviesContract.FavouritesEntry.COLUMN_VOTE_AVERAGE, movie.getVoteAverage());
        contentValues.put(MoviesContract.FavouritesEntry.COLUMN_BACKDROP_PATH, movie.getBackdropPath());
        contentValues.put(MoviesContract.FavouritesEntry.COLUMN_POSTER_PATH, movie.getPosterPath());
        mContext.getContentResolver().insert(MoviesContract.FavouritesEntry.CONTENT_URI, contentValues);
    }

    @Override
    public void deleteFavourite(Movie movie) {
        int id = movie.getId();

        String stringId = Integer.toString(id);
        Uri uri = MoviesContract.FavouritesEntry.CONTENT_URI;
        uri = uri.buildUpon().appendPath(stringId).build();

        mContext.getContentResolver().delete(uri, null, null);

    }

    @Override
    public boolean isFavourite(Movie movie) {
        boolean isFav = false;
        Cursor c = mContext.getContentResolver()
                .query(MoviesContract.FavouritesEntry.CONTENT_URI, null, MoviesContract.FavouritesEntry._ID + " = " + movie.getId(), null, null);
        if (c != null && c.getCount() > 0) {
            isFav = true;
            c.close();
        }
        return isFav;
    }
}
