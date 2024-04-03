package com.example.moviemate;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private String LOG_TAG = "MovieAdapter";
    private Context context;
    private ArrayList<Movie> movieList;
    private OnItemClickListener listener;

    // Constructor to initialize the adapter with required parameters
    public MovieAdapter(Context context, ArrayList<Movie> movieList, OnItemClickListener listener) {
        this.context = context;
        this.movieList = movieList;
        this.listener = listener;
    }

    // onCreateViewHolder method inflates the item view layout
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        // Inflate an item view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_movie_layout, parent, false);
        MovieViewHolder holder = new MovieViewHolder(view);
        return holder;
    }

    // onBindViewHolder method binds data to the view elements
    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.tvMovieName.setText(movieList.get(position).getTitle());
        holder.tvMovieRating.setText(String.valueOf(movieList.get(position).getVoteAverage()));
        holder.tvMovieLength.setText(String.valueOf(movieList.get(position).getRuntime()));
        holder.tvMovieReleaseDate.setText(movieList.get(position).getReleaseDate());
        holder.tvMovieCategory.setText(String.valueOf(movieList.get(position).getRevenue()));

        Glide.with(this.context).load("https://image.tmdb.org/t/p/original" +movieList.get(position).getPosterPath()).into(holder.ivMoviePicture);
    }

    // getItemCount method returns the total number of items in the list
    @Override
    public int getItemCount() {
        return movieList.size();
    }

    // setData method updates the data list and notifies the adapter of changes
    public void setData(List<Movie> movies) {
        movieList.clear();
        movieList.addAll(movies);
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvMovieName;
        TextView tvMovieRating;
        TextView tvMovieLength;
        TextView tvMovieReleaseDate;
        TextView tvMovieCategory;
        ImageView ivMoviePicture;

        // Constructor initializes view elements and sets click listener
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMovieName = itemView.findViewById(R.id.tv_movieName);
            tvMovieRating = itemView.findViewById(R.id.tv_movieRating);
            tvMovieLength = itemView.findViewById(R.id.tv_movieLength);
            tvMovieReleaseDate = itemView.findViewById(R.id.tv_movieReleaseDate);
            tvMovieCategory = itemView.findViewById(R.id.tv_movieCategory);
            ivMoviePicture = itemView.findViewById(R.id.iv_MoviePicture);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Log.d(LOG_TAG, "Listitem onClick: " + position);

                    Movie movie = movieList.get(position);


                    Intent intent = new Intent(view.getContext(), MovieDetailActivity.class);

                    intent.putExtra(MainActivity.EXTRA_ADDED_MOVIE, movie);

                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    // Interface for item click listener
    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }


    // Method to clear all data from the adapter
    public void clearData() {
        movieList.clear();
        notifyDataSetChanged();
    }
}
