package com.example.moviemate.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moviemate.enities.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    // Insert a movie into the database
    @Insert
    void insertMovie(Movie movie);

    // Delete a movie from the database
    @Delete
    void deleteMovie(Movie movie);

    // Update an existing movie in the database
    @Update
    void updateMovie(Movie movie);

    // Delete all movies from the database
    @Query("DELETE FROM movies")
    void deleteAllMovies();

    // Retrieve all movies from the database sorted by name
    @Query("SELECT * FROM movies ORDER BY title ASC")
    LiveData<List<Movie>> getAllMovies();
}
