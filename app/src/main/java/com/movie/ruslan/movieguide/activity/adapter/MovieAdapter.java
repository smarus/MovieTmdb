package com.movie.ruslan.movieguide.activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.movie.ruslan.movieguide.R;
import com.movie.ruslan.movieguide.activity.utils.ImageUtils;
import com.movie.ruslan.movieguide.model.database.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ruslan on 18.04.18.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder>  {

    private List<Movie> movieList;
    private final int mImageHeight;
    private final int mImageWidth;
    MovieInterface movieInterface;

    public MovieAdapter(int mImageHeight, int mImageWidth, MovieInterface movieInterface) {
        this.movieList = new ArrayList<>();
        this.mImageHeight = mImageHeight;
        this.mImageWidth = mImageWidth;
        this.movieInterface = movieInterface;
    }

    public interface MovieInterface{
        void onClickMovie(View view,Movie movie);
    }
    public void changeMovieDataSet(List<Movie> movies){
        movieList.clear();
        movieList.addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.recycler_item,null);
        ImageView imageView = view.findViewById(R.id.image);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.height = mImageHeight;
        layoutParams.width = mImageWidth;
        imageView.requestLayout();
        return  new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.bind(movie);

    }

    @Override
    public int getItemCount() {
        return movieList.size();


    }

    public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.image)
        ImageView imageView;

        public MovieHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this ,itemView);
        }

        @Override
        public void onClick(View view) {
        movieInterface.onClickMovie(view,movieList.get(getAdapterPosition()));
        }

        public void bind(Movie movie){
            ImageUtils.loadImage(imageView,movie.getPosterPath(),ImageUtils.WIDTH_185);
        }
    }
}
