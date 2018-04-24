package com.movie.ruslan.movieguide.activity.utils;

import android.content.Context;
import android.widget.ImageView;

import com.movie.ruslan.movieguide.BuildConfig;
import com.squareup.picasso.Picasso;

/**
 * Created by ruslan on 22.04.18.
 */

public class ImageUtils {
    public static final String WIDTH_185 = "w185";
    public static final String WIDTH_780 = "w780";


    public static void loadImage(ImageView imageView,String posterPath,String size){
        String allUrl = BuildConfig.IMAGES_BASE_URL+size+posterPath;
        Picasso.with(imageView.getContext())
                .load(allUrl)
                .into(imageView);
    }
}
