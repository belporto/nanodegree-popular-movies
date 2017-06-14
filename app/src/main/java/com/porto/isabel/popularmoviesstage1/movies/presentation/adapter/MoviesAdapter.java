package com.porto.isabel.popularmoviesstage1.movies.presentation.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.porto.isabel.popularmoviesstage1.R;
import com.porto.isabel.popularmoviesstage1.model.moviedb.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by belporto on 14/06/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {

    private List<Movie> mMovies = new ArrayList<>();
    private Context mContext;

    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_grid_item, parent, false);
        return new MoviesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesAdapterViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        String  uri = "http://image.tmdb.org/t/p/w185" + movie.getPosterPath();
        Picasso.with(mContext).load(uri).into(holder.mPosterImageView);
    }

    public void setData(List<Movie> movies) {
        mMovies = movies;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    class MoviesAdapterViewHolder extends RecyclerView.ViewHolder {

        final ImageView mPosterImageView;

        MoviesAdapterViewHolder(View view) {
            super(view);
            mPosterImageView = (ImageView) view.findViewById(R.id.movie_poster_image_view);
        }

    }
}
