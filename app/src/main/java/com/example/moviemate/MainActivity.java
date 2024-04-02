package com.example.moviemate;

import com.example.moviemate.R;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.moviemate.data.MovieAPI;
import com.example.moviemate.data.MovieAPITask;
import com.example.moviemate.data.MovieParser;

import java.util.ArrayList;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity implements MovieParser.OnMovieParserListener, MovieAdapter.OnItemClickListener {
    private String LOG_TAG = "MainActivity";

    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;
    private MovieAPI movieApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize MovieAPI connection
        movieApi = new MovieAPI();

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.Rv_movieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movieApi.fetchAllMovies(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // destroy MovieAPI connection
        if (movieApi != null) movieApi.disconnect();
    }

    @Override
    public void onParsedAllMovies(ArrayList<Movie> movies) {
        Log.i(LOG_TAG, "onParsedAllMovies: movies size = "+ movies.size());
        if (mAdapter == null) {
            mAdapter = new MovieAdapter(this, movies, this);
            recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setData(movies); // Assuming you have a method to update data in the adapter
            mAdapter.notifyDataSetChanged();
        }
    }

    // Callback method invoked when a cocktail item is clicked
    @Override
    public void onItemClick(Movie movie) {
        // Handle item click by navigating to CocktailDetailActivity
        Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
        intent.putExtra("MOVIE_DATA", movie);
        startActivity(intent);
    }
}