package com.movie.ruslan.movieguide.model.responses;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.movie.ruslan.movieguide.model.database.Review;

import java.util.ArrayList;
import java.util.List;


public class ReviewsResponse {

    @SerializedName("results")
    private List<Review> mReviews;

    @NonNull
    public List<Review> getReviews() {
        if (mReviews == null) {
            mReviews = new ArrayList<>();
        }
        return mReviews;
    }
}
