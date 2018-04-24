package com.movie.ruslan.movieguide.network;

import com.movie.ruslan.movieguide.model.responses.MovieResponse;



import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by ruslan on 18.04.18.
 */

public interface MovieService {
    @GET("popular/")
    Flowable<MovieResponse> getPopularMovies();

    Flowable<MovieResponse> getTopRated();
}
