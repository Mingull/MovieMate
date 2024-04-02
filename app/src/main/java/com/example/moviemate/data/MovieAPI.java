package com.example.moviemate.data;

import android.util.Log;

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

    public void fetchMovie(String movieId, MovieParser.OnMovieParserListener parserListener) {
        fetchData("/movie/" + movieId, "fetchMovie");
        this.parserListener = parserListener;
    }

    @Override
    public void onFetchedAllMovies(String moviesResponse) {
        MovieParser parser = new MovieParser(this.parserListener);
        parser.execute(moviesResponse);
    }

    @Override
    public void onFetchedMovie(String movie) {

    }
}
 