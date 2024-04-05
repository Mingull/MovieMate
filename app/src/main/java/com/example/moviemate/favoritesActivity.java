package com.example.moviemate;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviemate.data.repositories.FavoriteRepository;
import com.example.moviemate.data.repositories.MovieRepository;
import com.example.moviemate.enities.Favorite;

import java.util.ArrayList;


public class FavoritesActivity extends AppCompatActivity {
    private static final String TAG = "FavoritesActivity";
    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle("Favorite Movies");
        }

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.rv_favorite_movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize and set adapter
        mAdapter = new MovieAdapter(this, new ArrayList<>(), null); // Pass an empty list initially
        recyclerView.setAdapter(mAdapter);

        // Populate RecyclerView with favorites from the database
        populateFavorites();
        // Set onClickListener for delete button
        Button deleteButton = findViewById(R.id.btn_delete_favorites);
        deleteButton.setOnClickListener(view -> deleteAllFavorites());
    }

    // Method to populate RecyclerView with favorites from the database
    private void populateFavorites() {
        MovieRepository repository = new MovieRepository(getApplication());
        repository.getAllMovies().observe(this, movies -> {
            Log.d(TAG, "Favorites retrieved successfully. Count: " + movies.size());
            mAdapter.setData(movies); // Update the adapter with new data
        });
    }

    // Method to delete all favorite movies
    private void deleteAllFavorites() {
        MovieRepository repository = new MovieRepository(getApplication());
        repository.deleteAllMovies();
    }

    // Update RecyclerView after deleting all favorites
    private void updateRecyclerView() {
        mAdapter.clearData();
    }
}
