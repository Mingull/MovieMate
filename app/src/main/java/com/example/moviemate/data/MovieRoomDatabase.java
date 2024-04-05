package com.example.moviemate.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.moviemate.data.dao.FavoriteDao;
import com.example.moviemate.data.dao.MovieDao;
import com.example.moviemate.enities.Favorite;
import com.example.moviemate.enities.Movie;
import com.example.moviemate.Converters;


@Database(entities = {Movie.class, Favorite.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class MovieRoomDatabase extends RoomDatabase {

    private static volatile MovieRoomDatabase INSTANCE;

    public static MovieRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MovieRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MovieRoomDatabase.class, "movie_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract MovieDao movieDao();
    public abstract FavoriteDao favoriteDao();
}
