package com.example.moviemate.data;

import android.util.Log;

import com.example.moviemate.Movie;

import java.util.ArrayList;

public class MovieAPI implements MovieAPITask.OnMovieAPITaskListener {
    private static String LOG_TAG = "MovieAPI";
    final private static String baseURL = "https://api.themoviedb.org/3";
    final private static String API_KEY = "1600b4a840ea493d1acb324fc49be52a";
    private MovieAPITask.OnMovieAPITaskListener listener;
    private MovieParser.OnMovieParserListener parserListener;

    public MovieAPI() {
        this.listener = this;
    }

    public void disconnect() {
        this.listener = null;
    }

    private void fetchData(String path, String action) {
        Log.i(LOG_TAG, "fetching data");
        MovieAPITask task = new MovieAPITask(listener);
        task.execute(baseURL + path + "?api_key=" + API_KEY + "&page=1", action); // Add &page=1 for the first page
    }

    public void fetchAllMovies(MovieParser.OnMovieParserListener parserListener) {
        fetchData("/discover/movie", "fetchAllMovies");
        this.parserListener = parserListener;
    }

    public void fetchAllMoviesWithRuntime(ArrayList<Movie> movies, MovieParser.OnMovieParserListener parserListener) {
        for (Movie movie : movies) fetchData("/movie/" + movie.getId(), "fetchMovie");
        this.parserListener = parserListener;
    }

    @Override
    public void onFetchedAllMovies(String moviesResponse) {
        MovieParser parser = new MovieParser(this.parserListener);
        parser.execute(moviesResponse, "parseMovies");
    }

    @Override
    public void onFetchedMovie(String movie) {
        MovieParser parser = new MovieParser(this.parserListener);
        parser.execute(movie, "parseMoviesWithRuntime");
    }
}
 