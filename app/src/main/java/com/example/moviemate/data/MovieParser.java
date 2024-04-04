package com.example.moviemate.data;

import android.os.AsyncTask;
import android.util.Log;

import com.example.moviemate.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieParser extends AsyncTask<String, Void, ArrayList<Object>> {
    private static String LOG_TAG = "MovieParser";
    private MovieParser.OnMovieParserListener listener;
    private ArrayList<Movie> tempMovies;

    public MovieParser(MovieParser.OnMovieParserListener listener) {
        super();
        this.listener = listener;
    }

    @Override
    protected ArrayList<Object> doInBackground(String... params) {
        Log.i(LOG_TAG, "doInBackground");
        if (params[0] == null) return null;
        if (params[1] == null) return null;

        String response = params[0];
        String action = params[1];

        Log.i(LOG_TAG, "doInBackground - response: " + response);

        return jsonParseResponse(response, action);
    }

    @Override
    protected void onPostExecute(ArrayList<Object> response) {
        if(response == null) return;
        Log.i(LOG_TAG, "onPostExecute: " + response.toString());
        String action = (String) response.get(0);

        ArrayList<Object> rest = new ArrayList<>(response.subList(1, response.size()));

        Log.i(LOG_TAG, "onPostExecute - rest size: " + rest.size());
        if (action.equals("parseMovies")) {
            // check if the rest is an ArrayList of Movies
            if (rest.size() != 0) {
                ArrayList<Movie> movies = new ArrayList<>();
                for (Object movie : rest) {
                    movies.add((Movie) movie);
                }
                this.listener.onParsedAllMovies(movies);
            }
        }
        if (action.equals("parseMovieVideos")) {
            if (rest.size() != 0) {
                ArrayList<Movie.Video> videos = new ArrayList<>();
                for (Object movie : rest) {
                    videos.add((Movie.Video) movie);
                }
                this.listener.onParsedMovieVideos(videos);
            }
        }
    }

    // Interface to communicate with the caller
    public interface OnMovieParserListener {
        default void onParsedAllMovies(ArrayList<Movie> movies) {
        }

        default void onParsedMovieVideos(ArrayList<Movie.Video> videos) {
        }

    }

    // Method to parse JSON response and extract movie data
    private ArrayList<Object> jsonParseResponse(String response, String action) {
        Log.i(LOG_TAG, "jsonParse Response: " + response);
        Log.i(LOG_TAG, "jsonParse action: " + action);
        ArrayList<Object> returnList = new ArrayList<>();

        returnList.add(action);
        try {
            if (action.equals("parseMovies")) {
                JSONArray results = getJsonResults(response, "results");
                for (int i = 0; i < results.length(); i++) {
                    JSONObject jsonObject = results.getJSONObject(i);
                    // Parse movie data from jsonObject
                    Movie movie = parseMovie(jsonObject);
                    returnList.add(movie);
                }
            }

            if (action.equals("parseMovieVideos")) {
                JSONArray results = getJsonResults(response, "results");
                for (int i = 0; i < results.length(); i++) {
                    JSONObject jsonObject = results.getJSONObject(i);
                    // Parse movie data from jsonObject
                    Movie.Video movie = parseMovieVideo(jsonObject);
                    returnList.add(movie);
                }
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error parsing JSON", e);
        }

        Log.i(LOG_TAG, "returnList: " + returnList);

        return returnList;
    }

    private static JSONArray getJsonResults(String response, String responseName) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);

        return jsonObject.getJSONArray(responseName);
    }

    private Movie parseMovie(JSONObject jsonObject) throws JSONException {
        int id = jsonObject.optInt("id", -1);
        boolean adult = jsonObject.optBoolean("adult", false);
        String backdropPath = jsonObject.optString("backdrop_path", "");
        String imdbId = jsonObject.optString("imdb_id", "");
        String originalLanguage = jsonObject.optString("original_language", "");
        String originalTitle = jsonObject.optString("original_title", "");
        String overview = jsonObject.optString("overview", "");
        double popularity = jsonObject.optDouble("popularity", 0.0);
        String posterPath = jsonObject.optString("poster_path", "");
        String releaseDate = jsonObject.optString("release_date", "");
        int revenue = jsonObject.optInt("revenue", 347255055);
        int runtime = jsonObject.optInt("runtime", 105);
        String status = jsonObject.optString("status", "");
        String tagline = jsonObject.optString("tagline", "");
        String title = jsonObject.optString("title", "");
        boolean video = jsonObject.optBoolean("video", false);
        double voteAverage = jsonObject.optDouble("vote_average", 0.0);
        int voteCount = jsonObject.optInt("vote_count", 0);

        // Create Genre list (assuming it's an array in the JSON)
        List<Movie.Genre> genres = new ArrayList<>();
        JSONArray genreArray = jsonObject.optJSONArray("genres");
        if (genreArray != null) {
            for (int i = 0; i < genreArray.length(); i++) {
                JSONObject genreObject = genreArray.getJSONObject(i);
                int genreId = genreObject.optInt("id", -1);
                String genreName = genreObject.optString("name", "");
                Movie.Genre movieGenre = new Movie.Genre();
                movieGenre.setGenreId(genreId);
                movieGenre.setGenreName(genreName);
                genres.add(movieGenre);
            }
        }

        // Create Production Company list (assuming it's an array in the JSON)
        List<Movie.ProductionCompany> productionCompanies = new ArrayList<>();
        JSONArray companyArray = jsonObject.optJSONArray("production_companies");
        if (companyArray != null) {
            for (int i = 0; i < companyArray.length(); i++) {
                JSONObject companyObject = companyArray.getJSONObject(i);
                int companyId = companyObject.optInt("id", -1);
                String companyName = companyObject.optString("name", "");
                String companyLogoPath = companyObject.optString("logo_path", "");
                String companyOriginCountry = companyObject.optString("origin_country", "");
                Movie.ProductionCompany productionCompany = new Movie.ProductionCompany();
                productionCompany.setCompanyId(companyId);
                productionCompany.setCompanyName(companyName);
                productionCompany.setLogoPath(companyLogoPath);
                productionCompany.setOriginCountry(companyOriginCountry);
                productionCompanies.add(productionCompany);
            }
        }

        // Create Production Country list (assuming it's an array in the JSON)
        List<Movie.ProductionCountry> productionCountries = new ArrayList<>();
        JSONArray countryArray = jsonObject.optJSONArray("production_countries");
        if (countryArray != null) {
            for (int i = 0; i < countryArray.length(); i++) {
                JSONObject countryObject = countryArray.getJSONObject(i);
                String countryIso = countryObject.optString("iso_3166_1", "");
                String countryName = countryObject.optString("name", "");
                Movie.ProductionCountry productionCountry = new Movie.ProductionCountry();
                productionCountry.setIso_3166_1(countryIso);
                productionCountry.setCountryName(countryName);
                productionCountries.add(productionCountry);
            }
        }

        // Create Spoken Language list (assuming it's an array in the JSON)
        List<Movie.SpokenLanguage> spokenLanguages = new ArrayList<>();
        JSONArray languageArray = jsonObject.optJSONArray("spoken_languages");
        if (languageArray != null) {
            for (int i = 0; i < languageArray.length(); i++) {
                JSONObject languageObject = languageArray.getJSONObject(i);
                String languageEnglishName = languageObject.optString("english_name", "");
                String languageIso_639_1 = languageObject.optString("iso_639_1", "");
                String languageName = languageObject.optString("name", "");
                Movie.SpokenLanguage spokenLanguage = new Movie.SpokenLanguage();
                spokenLanguage.setEnglishName(languageEnglishName);
                spokenLanguage.setLanguageName(languageName);
                spokenLanguages.add(spokenLanguage);
            }
        }

        return new Movie(id, adult, backdropPath, genres, imdbId, originalLanguage, originalTitle, overview, popularity,
                posterPath, productionCompanies, productionCountries, releaseDate, revenue, runtime, spokenLanguages,
                status, tagline, title, video, voteAverage, voteCount);
    }


    private Movie.Video parseMovieVideo(JSONObject jsonObject) throws JSONException {
        String id = jsonObject.optString("id", "");
        String iso_639_1 = jsonObject.optString("iso_639_1", "");
        String iso_3166_1 = jsonObject.optString("iso_3166_1", "");
        String name = jsonObject.optString("name", "");
        String key = jsonObject.optString("key", "");
        String site = jsonObject.optString("site", "");
        int size = jsonObject.optInt("size", 1080);
        String type = jsonObject.optString("type", "");
        boolean official = jsonObject.optBoolean("official", false);
        String published_at = jsonObject.optString("published_at", "");

        return new Movie.Video(id, name, site, key, type, size, official, iso_639_1, iso_3166_1, published_at);
    }
}
