package com.example.moviemate.data.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.moviemate.data.MovieRoomDatabase;
import com.example.moviemate.data.dao.FavoriteDao;
import com.example.moviemate.enities.Favorite;
import com.example.moviemate.enities.Movie;

import java.util.List;

public class FavoriteRepository {

    private static final String LOG_TAG = "FavoriteRepository";
    private FavoriteDao favoriteDao;
    private LiveData<List<Favorite>> allFavorites;

    // Constructor to initialize the database and DAO
    public FavoriteRepository(Application application) {
        Log.i(LOG_TAG, "MovieRepository Constructor");
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        favoriteDao = db.favoriteDao();
        allFavorites = favoriteDao.getAllFavorites();
    }

    // Method to get all Movies as LiveData
    public LiveData<List<Favorite>> getAllFavorites() {
        Log.i(LOG_TAG, "getAllMovies");
        return allFavorites;
    }

    public boolean favoriteExists(Movie movie) {
        return (allFavorites.getValue() != null && allFavorites.getValue().contains(movie));
    }

    // Method to insert a movie into the database
    public void insert(Favorite favorite) {
        Log.i(LOG_TAG, "insert");
        new InsertFavoriteAsyncTask(favoriteDao).execute(favorite);
    }

    // AsyncTask to insert a movie into the database
    private static class InsertFavoriteAsyncTask extends AsyncTask<Favorite, Void, Void> {
        private FavoriteDao mAsyncTaskDao;

        InsertFavoriteAsyncTask(FavoriteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Favorite... params) {
            Log.i(LOG_TAG, "InsertMovieAsyncTask doInBackground");
            mAsyncTaskDao.insertFavorite(params[0]);
            return null;
        }
    }

    public void delete(Favorite favorite) {
        Log.i(LOG_TAG, "deleteAllMovies");
        new DeleteFavoriteAsyncTask(favoriteDao).execute(favorite);
    }

    // AsyncTask to delete all movies from the database
    private static class DeleteFavoriteAsyncTask extends AsyncTask<Favorite, Void, Void> {
        private FavoriteDao mMovieDaoAsyncTask;

        public DeleteFavoriteAsyncTask(FavoriteDao movieDao) {
            mMovieDaoAsyncTask = movieDao;
        }

        @Override
        protected Void doInBackground(Favorite... favorites) {
            Log.i(LOG_TAG, "DeleteAllMoviesAsyncTask doInBackground");
            mMovieDaoAsyncTask.deleteFavorite(favorites[0]);
            return null;
        }
    }

    public void deleteAllFavorites() {
        Log.i(LOG_TAG, "deleteAllMovies");
        new DeleteAllFavoritesAsyncTask(favoriteDao).execute();
    }

    // AsyncTask to delete all movies from the database
    private static class DeleteAllFavoritesAsyncTask extends AsyncTask<Void, Void, Void> {
        private FavoriteDao mMovieDaoAsyncTask;

        public DeleteAllFavoritesAsyncTask(FavoriteDao movieDao) {
            mMovieDaoAsyncTask = movieDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.i(LOG_TAG, "DeleteAllMoviesAsyncTask doInBackground");
            mMovieDaoAsyncTask.deleteAllFavorites();
            return null;
        }
    }
}
