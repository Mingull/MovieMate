package com.example.moviemate;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieDetailActivity extends AppCompatActivity {
    private final String LOG_TAG = this.getClass().getSimpleName();

    TextView tvMovieName;
    TextView tvMovieRating;
    TextView tvMovieLength;
    TextView tvMovieReleaseDate;
    TextView tvMovieCategory;
    ImageView ivMoviePicture;

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
        tvMovieLength = findViewById(R.id.tv_detail_rating);
        tvMovieReleaseDate = findViewById(R.id.tv_detail_rating);
        tvMovieRating = findViewById(R.id.tv_detail_rating);
    }
}