package com.porto.isabel.popularmovies.screens.review.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.porto.isabel.popularmovies.R;
import com.porto.isabel.popularmovies.model.moviedb.Review;

import java.util.ArrayList;
import java.util.List;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewAdapterViewHolder> {

    private List<Review> mReviews = new ArrayList<>();


    @Override
    public ReviewAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ReviewAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewAdapterViewHolder holder, int position) {
        Review review = mReviews.get(position);
        holder.mAuthor.setText(review.getAuthor());
        holder.mComment.setText(review.getContent());
    }

    public void setData(List<Review> reviews) {
        mReviews = reviews;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    class ReviewAdapterViewHolder extends RecyclerView.ViewHolder {

        final TextView mAuthor;
        final TextView mComment;

        ReviewAdapterViewHolder(View view) {
            super(view);
            mAuthor = (TextView) view.findViewById(R.id.review_item_author);
            mComment = (TextView) view.findViewById(R.id.review_item_commnet);
        }

    }
}
