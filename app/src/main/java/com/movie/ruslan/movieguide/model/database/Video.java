package com.movie.ruslan.movieguide.model.database;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by ruslan on 18.04.18.
 */

public class Video extends RealmObject {

    @SerializedName("key")
    private String mKey;

    @SerializedName("name")
    private String mName;

    @NonNull
    public String getKey() {
        return mKey;
    }

    public void setKey(@NonNull String key) {
        mKey = key;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public void setName(@NonNull String name) {
        mName = name;
    }
}