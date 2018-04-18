package com.movie.ruslan.movieguide.model.database;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by ruslan on 18.04.18.
 */

public class Review extends RealmObject {

    @SerializedName("author")
    private String author;

    @SerializedName("content")
    private String content;

    @NonNull
    public String getAuthor() {
        return author;
    }

    public void setAuthor(@NonNull String author) {
        this.author = author;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
    }
}
