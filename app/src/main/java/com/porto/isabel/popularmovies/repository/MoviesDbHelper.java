package com.porto.isabel.popularmovies.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MoviesDbHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "movies.db";

    private static final int DATABASE_VERSION = 1;

    public MoviesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_FAV_TABLE =

                "CREATE TABLE " + MoviesContract.FavouritesEntry.TABLE_NAME + " (" +

                        MoviesContract.FavouritesEntry._ID + " INTEGER ," +
                        MoviesContract.FavouritesEntry.COLUMN_TITLE + " TEXT , " +
                        MoviesContract.FavouritesEntry.COLUMN_RELEASE_DATE + " TEXT ," +
                        MoviesContract.FavouritesEntry.COLUMN_VOTE_AVERAGE + " REAL , " +
                        MoviesContract.FavouritesEntry.COLUMN_OVERVIEW + " TEXT , " +

                        " UNIQUE (" + MoviesContract.FavouritesEntry._ID + ") ON CONFLICT REPLACE);";


        sqLiteDatabase.execSQL(SQL_CREATE_FAV_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoviesContract.FavouritesEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}