package com.example.moviemate.data;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MovieAPITask extends AsyncTask<String, Void, ArrayList<String>> {
    private static final String LOG_TAG = "MovieAPITask";
    private OnMovieAPITaskListener listener;

    public MovieAPITask(OnMovieAPITaskListener listener) {
        super();
        this.listener = listener;
    }

    @Override
    protected ArrayList<String> doInBackground(String... params) {
        if (params[0] == null) return null;
        if (params[1] == null) return null;

        ArrayList<String> returnList = new ArrayList<>();
        String urlString = params[0];
        String action = params[1];

        Log.i(LOG_TAG, "doInBackground url:" + urlString + " action: " + action);

        InputStream inputStream = null;
        int responseCode = -1;
        String response = null;

        try {
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();

            if (!(urlConnection instanceof HttpURLConnection)) {
                // Invalid URL
                return null;
            }

            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            responseCode = httpConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpConnection.getInputStream();
                response = getStringFromInputStream(inputStream);
                Log.d(LOG_TAG, "response = " + response);

            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "doInBackground MalformedURLEx " + e.getLocalizedMessage());
        } catch (IOException e) {
            Log.e(LOG_TAG, "doInBackground IOException " + e.getLocalizedMessage());
        }

        returnList.add(action);
        returnList.add(response);

        return returnList;
    }

    @Override
    protected void onPostExecute(ArrayList<String> response) {
        super.onPostExecute(response);
        Log.i(LOG_TAG, "onPostExecute: action = " + response.get(0));

        switch (response.get(0)) {
            case "fetchAllMovies":
                listener.onFetchedAllMovies(response.get(1));
                break;
            case "fetchMovie":
                listener.onFetchedMovie(response.get(1));
                break;
            default:
                break;
        }
    }

    // Interface to communicate with the caller
    public interface OnMovieAPITaskListener {
        void onFetchedAllMovies(String movies);

        void onFetchedMovie(String movie);
    }

    // Method to convert InputStream to String
    private static String getStringFromInputStream(InputStream inputStream) {
        Log.i(LOG_TAG, "getStringFromInputStream");
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
