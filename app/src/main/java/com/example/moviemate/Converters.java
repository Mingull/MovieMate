package com.example.moviemate;

import androidx.room.TypeConverter;

import com.example.moviemate.enities.Movie;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class Converters {
    @TypeConverter
    public static List<Movie.Genre> fromGenreString(String value) {
        Type listType = new TypeToken<List<Movie.Genre>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String toGenreString(List<Movie.Genre> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<Movie.ProductionCompany> fromProductionCompanyString(String value) {
        Type listType = new TypeToken<List<Movie.ProductionCompany>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String toProductionCompanyString(List<Movie.ProductionCompany> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<Movie.ProductionCountry> fromProductionCountryString(String value) {
        Type listType = new TypeToken<List<Movie.ProductionCountry>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String toProductionCountryString(List<Movie.ProductionCountry> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<Movie.SpokenLanguage> fromSpokenLanguageString(String value) {
        Type listType = new TypeToken<List<Movie.SpokenLanguage>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String toSpokenLanguageString(List<Movie.SpokenLanguage> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static ArrayList<Movie.Video> fromVideosString(String value) {
        Type listType = new TypeToken<ArrayList<Movie.Video>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String toVideoString(ArrayList<Movie.Video> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
