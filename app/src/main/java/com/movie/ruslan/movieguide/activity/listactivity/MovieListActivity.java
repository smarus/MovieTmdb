package com.movie.ruslan.movieguide.activity.listactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.movie.ruslan.movieguide.R;
import com.movie.ruslan.movieguide.activity.adapter.MovieAdapter;
import com.movie.ruslan.movieguide.activity.detail.DetailActivity;
import com.movie.ruslan.movieguide.model.database.Movie;
import com.movie.ruslan.movieguide.model.responses.MovieResponse;
import com.movie.ruslan.movieguide.network.ApiMovieFactory;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;

public class MovieListActivity extends AppCompatActivity implements MovieAdapter.MovieInterface {

    private Disposable disposable;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        int columns = 2;

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,columns);
        recyclerView.setLayoutManager(layoutManager);
        movieAdapter = createAdapter();
        recyclerView.setAdapter(movieAdapter);

        disposable = ApiMovieFactory.getMoviesService()
                .getPopularMovies()
                .map(MovieResponse::getMovies)
                .flatMap(movies -> {
                    Realm realmDefault = Realm.getDefaultInstance();
                    realmDefault.executeTransaction(realm -> {
                        realm.delete(Movie.class);
                        realm.insert(movies);
                    });
                    return Flowable.just(movies);
                })
                .onErrorResumeNext(throwable -> {
                    Log.e("TAG","Bad");
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<Movie> results = realm.where(Movie.class).findAll();
                    return Flowable.just(realm.copyFromRealm(results));
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(this::showProgressBar)
                .doAfterTerminate(this::hideProgressBar)
                .subscribe(this::showMovies);

    }


    private MovieAdapter createAdapter() {
        TypedValue typedValue = new TypedValue();
        getResources().getValue(R.dimen.rows_count, typedValue, true);
        float rowsCount = typedValue.getFloat();
        int actionBarHeight = getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)
                ? TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics())
                : 0;
        int imageHeight = (int) ((getResources().getDisplayMetrics().heightPixels - actionBarHeight) / rowsCount);

        int columns = 2;
        int imageWidth = getResources().getDisplayMetrics().widthPixels / columns;

        return new MovieAdapter(imageHeight, imageWidth, this);
    }

    @Override
    public void onClickMovie(View view, Movie movie) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("movie",movie);
        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void showMovies(List<Movie> movies){
        movieAdapter.changeMovieDataSet(movies);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void showProgressBar(Subscription subscription){
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
