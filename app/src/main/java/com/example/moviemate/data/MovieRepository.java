package com.example.moviemate.data;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.moviemate.Movie;

import java.util.List;

public class MovieRepository {

    private static final String LOG_TAG = "MovieRepository";
    private MovieDao mMovieDao;
    private LiveData<List<Movie>> mAllMovies;

    // Constructor to initialize the database and DAO
    public MovieRepository(Application application) {
        Log.i(LOG_TAG, "MovieRepository Constructor");
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        mMovieDao = db.movieDao();
        mAllMovies = mMovieDao.getAllMovies();
    }

    // Method to get all Movies as LiveData
    public LiveData<List<Movie>> getAllMovies() {
        Log.i(LOG_TAG, "getAllMovies");
        return mAllMovies;
    }

    // Method to insert a movie into the database
    public void insert(Movie movie) {
        Log.i(LOG_TAG, "insert");
        new InsertMovieAsyncTask(mMovieDao).execute(movie);
    }

    // AsyncTask to insert a movie into the database
    private static class InsertMovieAsyncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDao mAsyncTaskDao;

        InsertMovieAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie... params) {
            Log.i(LOG_TAG, "InsertMovieAsyncTask doInBackground");
            mAsyncTaskDao.insertMovie(params[0]);
            return null;
        }
    }

    public void deleteAllMovies() {
        Log.i(LOG_TAG, "deleteAllMovies");
        new DeleteAllMoviesAsyncTask(mMovieDao).execute();
    }

    // AsyncTask to delete all movies from the database
    private static class DeleteAllMoviesAsyncTask extends AsyncTask<Void, Void, Void> {
        private MovieDao mMovieDaoAsyncTask;

        public DeleteAllMoviesAsyncTask(MovieDao movieDao) {
            mMovieDaoAsyncTask = movieDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.i(LOG_TAG, "DeleteAllMoviesAsyncTask doInBackground");
            mMovieDaoAsyncTask.deleteAllMovies();
            return null;
        }
    }
}
