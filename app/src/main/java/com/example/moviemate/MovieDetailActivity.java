package com.example.moviemate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.moviemate.data.MovieParser;
import com.example.moviemate.data.repositories.FavoriteRepository;
import com.example.moviemate.data.repositories.MovieRepository;
import com.example.moviemate.enities.Favorite;
import com.example.moviemate.enities.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity implements MovieParser.OnMovieParserListener {
    private final String LOG_TAG = this.getClass().getSimpleName();
    TextView tvMovieName;
    TextView tvMovieRating;
    TextView tvMovieLength;
    TextView tvMovieReleaseDate;
    TextView tvMovieOverview;
    WebView wvMovieTrailer;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle("Movie Details");
//            toolbar.setDisplayHomeAsUpEnabled(true);

            Log.i(LOG_TAG, "new toolbar title" + toolbar.getTitle());
        }

        tvMovieName = findViewById(R.id.tv_detail_name);
        tvMovieRating = findViewById(R.id.tv_detail_rating);
        tvMovieLength = findViewById(R.id.tv_detail_length);
        tvMovieReleaseDate = findViewById(R.id.tv_detail_release_date);
        tvMovieOverview = findViewById(R.id.tv_detail_overview);
        wvMovieTrailer = findViewById(R.id.iv_detail_trailer);

        Intent intent = getIntent();
        movie = intent.getParcelableExtra(MainActivity.EXTRA_ADDED_MOVIE);
        MovieRepository repository = new MovieRepository(getApplication());

        if (movie != null) {
            if (movie.getVideos() == null) {
                fetchVideos(movie);
            } else {
                displayTrailer(movie.getVideos());
            }

            tvMovieName.setText(movie.getTitle());
            tvMovieRating.setText(String.valueOf(movie.getVoteAverage()));

            wvMovieTrailer.loadData("<h1 style=\"text-color: black;\">No trailer available</h1>", "text/html", "utf-8");

            tvMovieReleaseDate.setText("Release date: " + movie.getReleaseDate());

            tvMovieLength.setText("Runtime: " + movie.getRuntime());

            tvMovieOverview.setText(movie.getOverview());



            ImageView addToFavoritesButton = findViewById(R.id.iv_favorites_toggle);

            repository.getAllMovies().observe((LifecycleOwner) this, movies -> {
                if (movies.contains(movie)) {
                    addToFavoritesButton.setImageResource(R.drawable.round_star_outline_24);
                } else {
                    addToFavoritesButton.setImageResource(R.drawable.round_star_24);
                }
            });

            addToFavoritesButton.setOnClickListener(v -> {
                // Insert the cocktail into the database
                repository.getAllMovies().observe((LifecycleOwner) this, movies -> {
                    if (movies.contains(movie)) {
                        addToFavoritesButton.setImageResource(R.drawable.round_star_outline_24);
//                        removeMovieFromFavorites(movie);
                    } else {
                        addToFavoritesButton.setImageResource(R.drawable.round_star_24);
                        insertMovieToFavorites(movie);
                    }
                });
            });

            ImageView ivSeenMovie = findViewById(R.id.iv_seen_toggle);

            ImageView ivSendMovie = findViewById(R.id.iv_send_movie);
            // Set click listener for sharing
            ivSendMovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shareMovie();
                }
            });

            ImageView goHomeImageView = findViewById(R.id.iv_go_home);
            goHomeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Navigate back to MainActivity
                    onBackPressed();
                }
            });
        }
    }

    private void insertMovieToFavorites(Movie movie) {
        MovieRepository repository = new MovieRepository(getApplication());
        repository.insert(movie);
        Toast.makeText(this, "Movie added to favorites", Toast.LENGTH_SHORT).show();
    }
//    private void removeMovieFromFavorites(Movie movie) {
//        FavoriteRepository repository = new FavoriteRepository(getApplication());
//        Favorite favorite = repository.getAllFavorites().getValue().stream().filter(fav -> fav.getMovieId() == movie.getId()).findFirst().orElse(null);
//        repository.delete(favorite);
//        Toast.makeText(this, "Movie removed from favorites", Toast.LENGTH_SHORT).show();
//    }

    private void shareMovie() {
        // Create an Intent with action ACTION_SEND
        Intent shareIntent = new Intent(Intent.ACTION_SEND);

        // Set the type of content to "text/plain"
        shareIntent.setType("text/plain");

        // Build the content of the share message
        StringBuilder shareMessage = new StringBuilder();
        shareMessage.append("Check out this movie: ").append(tvMovieName.getText()).append("\n")
                .append("Rating: ").append(tvMovieRating.getText()).append("\n")
                .append("Release date: ").append(tvMovieReleaseDate.getText()).append("\n")
                .append("Runtime: ").append(tvMovieLength.getText()).append("\n\n")
                .append("View more information in the MovieMate app: ").append("https://MovieMate.com");

        // Set the content of the share message
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage.toString());

        // Start the activity with the share Intent
        startActivity(Intent.createChooser(shareIntent, "Share Movie"));
    }

    private void fetchVideos(Movie movie) {
        if (MainActivity.movieApi != null)
            MainActivity.movieApi.fetchMovieVideos(movie.getId(), this);
    }

    private void displayTrailer(ArrayList<Movie.Video> videos) {
        if (videos.size() > 0) {
            Movie.Video trailer = videos.stream().filter(video -> video.getType().equals("Trailer") && video.getVideoName().equals("Official Trailer")).findFirst().orElse(null);
            if (trailer != null) {
                String video = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/" + trailer.getKey() + "\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
                wvMovieTrailer.loadData(video, "text/html", "utf-8");
                wvMovieTrailer.getSettings().setJavaScriptEnabled(true);
                wvMovieTrailer.setWebChromeClient(new WebChromeClient());
            } else {
                // return a message if no trailer is available
                wvMovieTrailer.loadData("<h1 style=\"text-color: black;\">No trailer available</h1>", "text/html", "utf-8");
            }
        }
    }

    @Override
    public void onParsedMovieVideos(ArrayList<Movie.Video> videos) {
        Log.i(LOG_TAG, "onParsedMovieVideos: videos size = " + videos.size());
        if (movie != null) {
            movie.setVideos(videos);
            displayTrailer(videos);
        }
    }
}