package com.example.moviemate;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.moviemate.data.MovieAPI;
import com.example.moviemate.data.MovieParser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieParser.OnMovieParserListener, MovieAdapter.OnItemClickListener {
    private String LOG_TAG = "MainActivity";
    public static final String EXTRA_ADDED_MOVIE = "added_movie";

    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;
    public static MovieAPI movieApi;
    private ArrayList<Movie> movies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_menu_bar) {
            // Handle menu bar action
            return true;
        } else if (id == R.id.action_top_rated) {
            // Handle top-rated action
            return true;
        } else if (id == R.id.action_want_to_see) {
            // Handle want to see action
            return true;
        } else if (id == R.id.action_seen) {
            // Handle seen action
            return true;
        } else if (id == R.id.action_favorites) {
            // Handle favorites action
            Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onParsedAllMovies(ArrayList<Movie> movies) {
        Log.i(LOG_TAG, "onParsedAllMovies: movies size = " + movies.size());
        if (mAdapter == null) {
            mAdapter = new MovieAdapter(this, movies, this);
            recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setData(movies); // Assuming you have a method to update data in the adapter
            mAdapter.notifyDataSetChanged();
        }
    }


//    @Override
//    public void onParsedAllMovies(ArrayList<Movie> movies) {
//        if (movieApi == null) return;
//
//        movieApi.fetchAllMoviesWithRuntime(movies, this);
//    }


//    @Override
//    public void onParsedAllMoviesWithRuntime(ArrayList<Movie> movies) {
//        Log.i(LOG_TAG, "onParsedAllMovies: movies size = " + movies.size());
//        if (mAdapter == null) {
//            mAdapter = new MovieAdapter(this, movies, this);
//            recyclerView.setAdapter(mAdapter);
//        } else {
//            mAdapter.setData(movies); // Assuming you have a method to update data in the adapter
//            mAdapter.notifyDataSetChanged();
//        }
//    }

    // Callback method invoked when a cocktail item is clicked
    @Override
    public void onItemClick(Movie movie) {
        // Handle item click by navigating to CocktailDetailActivity
        Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
        intent.putExtra("MOVIE_DATA", movie);
        startActivity(intent);
    }
}