package Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// this is a plain old java object which encapsulates the idea of a movie
public class Movie {
    int movieID;
    String backdropPath;
    String posterPath;
    String title;
    String overview;
    double rating;
    //empty constructor needed by parceler
    public Movie(){

    }

    public Movie(JSONObject jsonObject) throws JSONException {
        backdropPath =  jsonObject.getString("backdrop_path");
        posterPath =  jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        rating = jsonObject.getDouble("vote_average");
        movieID = jsonObject.getInt("id");
    }
    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i =0 ; i < movieJsonArray.length(); i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342", posterPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342", backdropPath);
    }
    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public int getMovieID() {
        return movieID;
    }
}

