package com.example.moviemate.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
// import androidx.room.TypeConverters;
import com.example.moviemate.Movie;
// import com.example.moviemate.Converters;


@Database(entities = {Movie.class}, version = 1, exportSchema = false)
// @TypeConverters({Converters.class})
public abstract class MovieRoomDatabase extends RoomDatabase {

    private static volatile com.example.moviemate.data.MovieRoomDatabase INSTANCE;

    public static com.example.moviemate.data.MovieRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (com.example.moviemate.data.MovieRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    com.example.moviemate.data.MovieRoomDatabase.class, "movie_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract MovieDao movieDao();


}
