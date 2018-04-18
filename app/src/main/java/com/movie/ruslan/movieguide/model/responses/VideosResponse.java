package com.movie.ruslan.movieguide.model.responses;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.movie.ruslan.movieguide.model.database.Video;

import java.util.ArrayList;
import java.util.List;


public class VideosResponse {

    @SerializedName("results")
    private List<Video> mVideos;

    @NonNull
    public List<Video> getVideos() {
        if (mVideos == null) {
            return new ArrayList<>();
        }
        return mVideos;
    }
}
