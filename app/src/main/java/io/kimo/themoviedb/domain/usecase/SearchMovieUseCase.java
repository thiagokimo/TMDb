package io.kimo.themoviedb.domain.usecase;


import java.util.List;

import io.kimo.themoviedb.domain.BaseUseCase;
import io.kimo.themoviedb.domain.BaseUseCaseCallback;
import io.kimo.themoviedb.domain.entity.MovieEntity;
import io.kimo.themoviedb.domain.service.API;
import io.kimo.themoviedb.domain.service.response.SearchMovieResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SearchMovieUseCase extends BaseUseCase {

    public interface SearchMovieCallback extends BaseUseCaseCallback {
        void onMoviesSearched(List<MovieEntity> movieEntities);
    }

    private String apiKey;
    private String query;

    protected SearchMovieUseCase(String apiKey, String query, SearchMovieCallback callback) {
        super(callback);
        this.apiKey = apiKey;
        this.query = query;
    }

    @Override
    public void onRun() throws Throwable {
        API.http().searchMovie(apiKey, query, new Callback<SearchMovieResponse>() {
            @Override
            public void success(SearchMovieResponse searchMovieResponse, Response response) {
                ((SearchMovieCallback)callback).onMoviesSearched(searchMovieResponse.getResults());
            }

            @Override
            public void failure(RetrofitError error) {
                if(error.getKind() == RetrofitError.Kind.NETWORK) {
                    errorReason = NETWORK_ERROR;
                } else {
                    errorReason = error.getResponse().getReason();
                }
                onCancel();
            }
        });
    }
}
