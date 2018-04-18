package com.movie.ruslan.movieguide;

import android.app.Application;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;

/**
 * Created by ruslan on 18.04.18.
 */

public class MovieApplication extends Application {

    public static MovieApplication sInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        Picasso picasso = new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(this))
                .build();
        Picasso.setSingletonInstance(picasso);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .rxFactory(new RealmObservableFactory())
                .build();
        Realm.setDefaultConfiguration(config);
    }

    public static MovieApplication getAppContextInstance(){
        return sInstance;
    }
}
