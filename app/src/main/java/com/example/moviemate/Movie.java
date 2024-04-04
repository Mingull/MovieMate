package com.example.moviemate;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "movie_table")
@TypeConverters({Converters.class})
public class Movie implements Parcelable {

    // Movie attributes
    @PrimaryKey
    private int id;
    private boolean adult;
    private String backdropPath;
    private List<Genre> genres;
    private String imdbId;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private double popularity;
    private String posterPath;
    private List<ProductionCompany> productionCompanies;
    private List<ProductionCountry> productionCountries;
    private String releaseDate;
    private int revenue;
    private int runtime;
    private List<SpokenLanguage> spokenLanguages;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private double voteAverage;
    private int voteCount;
    private ArrayList<Video> videos;

    // Default constructor
    public Movie() {
    }

    // Constructor with attributes
    public Movie(int id, boolean adult, String backdropPath, List<Genre> genres, String imdbId, String originalLanguage, String originalTitle, String overview, double popularity, String posterPath, List<ProductionCompany> productionCompanies, List<ProductionCountry> productionCountries, String releaseDate, int revenue, int runtime, List<SpokenLanguage> spokenLanguages, String status, String tagline, String title, boolean video, double voteAverage, int voteCount) {
        this.id = id;
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.genres = genres;
        this.imdbId = imdbId;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.productionCompanies = productionCompanies;
        this.productionCountries = productionCountries;
        this.releaseDate = releaseDate;
        this.revenue = revenue;
        this.runtime = runtime;
        this.spokenLanguages = spokenLanguages;
        this.status = status;
        this.tagline = tagline;
        this.title = title;
        this.video = video;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.videos = new ArrayList<>();
    }

    protected Movie(Parcel in) {
        id = in.readInt();
        adult = in.readByte() != 0;
        backdropPath = in.readString();
        imdbId = in.readString();
        originalLanguage = in.readString();
        originalTitle = in.readString();
        overview = in.readString();
        popularity = in.readDouble();
        posterPath = in.readString();
        releaseDate = in.readString();
        revenue = in.readInt();
        runtime = in.readInt();
        status = in.readString();
        tagline = in.readString();
        title = in.readString();
        video = in.readByte() != 0;
        voteAverage = in.readDouble();
        voteCount = in.readInt();
        // Read other attributes from parcel
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };


    // Getters and setters for all attributes including genre
    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public ArrayList<Video> getVideos() {
        return this.videos;
    }

    public void setVideos(ArrayList<Video> videos) {
        this.videos = videos;
    }

    // Other getters and setters for remaining attributes

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeString(backdropPath);
        dest.writeString(imdbId);
        dest.writeString(originalLanguage);
        dest.writeString(originalTitle);
        dest.writeString(overview);
        dest.writeDouble(popularity);
        dest.writeString(posterPath);
        dest.writeString(releaseDate);
        dest.writeInt(revenue);
        dest.writeInt(runtime);
        dest.writeString(status);
        dest.writeString(tagline);
        dest.writeString(title);
        dest.writeByte((byte) (video ? 1 : 0));
        dest.writeDouble(voteAverage);
        dest.writeInt(voteCount);
        // Write other attributes to parcel
    }

    // Inner classes
    public static class Genre {
        private int genreId;
        private String genreName;

        public Genre() {

        }

        public int getGenreId() {
            return genreId;
        }

        public void setGenreId(int genreId) {
            this.genreId = genreId;
        }

        public String getGenreName() {
            return genreName;
        }

        public void setGenreName(String genreName) {
            this.genreName = genreName;
        }
    }

    public static class ProductionCompany {
        private int companyId;
        private String logoPath;
        private String companyName;
        private String originCountry;

        public ProductionCompany() {

        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public String getLogoPath() {
            return logoPath;
        }

        public void setLogoPath(String logoPath) {
            this.logoPath = logoPath;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getOriginCountry() {
            return originCountry;
        }

        public void setOriginCountry(String originCountry) {
            this.originCountry = originCountry;
        }

    }

    public static class ProductionCountry {
        private String iso_3166_1;
        private String countryName;

        public ProductionCountry() {
        }

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        public void setIso_3166_1(String iso_3166_1) {
            this.iso_3166_1 = iso_3166_1;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }
    }

    public static class SpokenLanguage {
        private String englishName;
        private String languageName;

        public SpokenLanguage() {
        }

        public String getEnglishName() {
            return englishName;
        }

        public void setEnglishName(String englishName) {
            this.englishName = englishName;
        }

        public String getLanguageName() {
            return languageName;
        }

        public void setLanguageName(String languageName) {
            this.languageName = languageName;
        }
    }

    public static class Video {
        private String id;
        private String videoName;
        private String site;
        private String key;
        private String type;
        private int size;
        private boolean official;
        private String iso_639_1;
        private String iso_3166_1;
        private String published_at;

        public Video() {
        }

        public Video(String id, String videoName, String site, String key, String type, int size, boolean official, String iso_639_1, String iso_3166_1, String published_at) {
            this.id = id;
            this.videoName = videoName;
            this.site = site;
            this.key = key;
            this.type = type;
            this.size = size;
            this.official = official;
            this.iso_639_1 = iso_639_1;
            this.iso_3166_1 = iso_3166_1;
            this.published_at = published_at;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVideoName() {
            return videoName;
        }

        public void setVideoName(String videoName) {
            this.videoName = videoName;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public boolean isOfficial() {
            return official;
        }

        public void setOfficial(boolean official) {
            this.official = official;
        }

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        public void setIso_3166_1(String iso_3166_1) {
            this.iso_3166_1 = iso_3166_1;
        }

        public String getPublished_at() {
            return published_at;
        }

        public void setPublished_at(String published_at) {
            this.published_at = published_at;
        }
    }
}
