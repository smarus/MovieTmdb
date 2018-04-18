package com.movie.ruslan.movieguide.model.responses;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.movie.ruslan.movieguide.model.database.Movie;

import java.util.ArrayList;
import java.util.List;


public class MoviesResponse {

    @SerializedName("results")
    private List<Movie> mMovies;

    @NonNull
    public List<Movie> getMovies() {
        if (mMovies == null) {
            return new ArrayList<>();
        }
        return mMovies;
    }

}
