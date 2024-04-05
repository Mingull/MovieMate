package com.example.moviemate.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moviemate.enities.Favorite;
import com.example.moviemate.enities.Movie;

import java.util.List;

@Dao
public interface FavoriteDao {

    // Insert a movie into favorites table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavorite(Favorite favorite);

    // Delete a movie from the database
    @Delete
    void deleteFavorite(Favorite favorite);

    // Update an existing movie in the database
    @Update
    void updateFavorite(Favorite favorite);

    // Delete all movies from the database
    @Query("DELETE FROM favorites")
    void deleteAllFavorites();

    // Retrieve all movies from the database sorted by name
    @Query("SELECT * FROM favorites")
    LiveData<List<Favorite>> getAllFavorites();
}
