package com.movie.ruslan.movieguide.network;

import com.movie.ruslan.movieguide.model.responses.MovieResponse;



import io.reactivex.Flowable;

/**
 * Created by ruslan on 18.04.18.
 */

public interface MovieService {

    Flowable<MovieResponse> getPopularMovies();
    Flowable<MovieResponse> getTopRated();
}
