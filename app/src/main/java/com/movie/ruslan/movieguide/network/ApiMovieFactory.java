package com.movie.ruslan.movieguide.network;

import android.support.annotation.NonNull;

import com.movie.ruslan.movieguide.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ruslan on 18.04.18.
 */

public final class ApiMovieFactory {

    private static OkHttpClient sClient;
    private static MovieService sService;

    @NonNull
    public static MovieService getMoviesService() {
        MovieService service = sService;
        if (service == null) {
            synchronized (ApiMovieFactory.class) {
                service = sService;
                if (service == null) {
                    service = sService = createService();
                }
            }
        }
        return service;
    }

    @NonNull
    private static MovieService createService() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.MOVIE_ENDPOINT)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MovieService.class);
    }

    @NonNull
    private static OkHttpClient getClient() {
        OkHttpClient client = sClient;
        if (client == null) {
            synchronized (ApiMovieFactory.class) {
                client = sClient;
                if (client == null) {
                    client = sClient = buildClient();
                }
            }
        }
        return client;
    }

    @NonNull
    private static OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new LogginInterceptor())
                .build();
    }
}