package com.example.moviemate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class MovieDetailActivity extends AppCompatActivity {
    private final String LOG_TAG = this.getClass().getSimpleName();
    TextView tvMovieName;
    TextView tvMovieRating;
    TextView tvMovieLength;
    TextView tvMovieReleaseDate;
    TextView tvMovieCategory;
    WebView wvMovieTrailer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle("Movie Details");
//            toolbar.setDisplayHomeAsUpEnabled(true);

            Log.i(LOG_TAG, "new toolbar title" + toolbar.getTitle());
        }

        tvMovieName = findViewById(R.id.tv_detail_name);
        tvMovieRating = findViewById(R.id.tv_detail_rating);
        tvMovieLength = findViewById(R.id.tv_detail_length);
        tvMovieReleaseDate = findViewById(R.id.tv_detail_release_date);
        wvMovieTrailer = findViewById(R.id.iv_detail_trailer);

        Intent intent = getIntent();
        Movie movie = (Movie) intent.getParcelableExtra(MainActivity.EXTRA_ADDED_MOVIE);
        Log.i(LOG_TAG, "movie " + movie);

        if (movie != null) {
            tvMovieName.setText(movie.getTitle());
            tvMovieRating.setText(String.valueOf(movie.getVoteAverage()));

            String video = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/tUesv5u5bvA\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
            wvMovieTrailer.loadData(video, "text/html", "utf-8");
            wvMovieTrailer.getSettings().setJavaScriptEnabled(true);
            wvMovieTrailer.setWebChromeClient(new WebChromeClient());

            tvMovieReleaseDate.setText("Release date: " + movie.getReleaseDate());

            tvMovieLength.setText("Runtime: " + movie.getRuntime());
        }
    }
}