package io.kimo.themoviedb.domain.service;


import io.kimo.themoviedb.domain.service.response.SearchMovieResponse;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

public class API {

    public static final String BASE_URL = "http://api.themoviedb.org/3";

    public interface ROUTES {
        //MOVIE SEARCH AUTOCOMPLETE
        @GET("/search/movie")
        void searchMovie(@Query("api_key")String apiKey, @Query("query") String query, Callback<SearchMovieResponse> callback);
    }

    public static ROUTES http() {
        return new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
                .create(ROUTES.class);
    }

}
