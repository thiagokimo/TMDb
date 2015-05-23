package io.kimo.tmdb.domain.usecase;

import java.util.List;

import io.kimo.tmdb.domain.BaseUseCase;
import io.kimo.tmdb.domain.BaseUseCaseCallback;
import io.kimo.tmdb.domain.entity.ImageEntity;
import io.kimo.tmdb.domain.service.API;
import io.kimo.tmdb.domain.service.response.GetMovieImagesResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GetMovieImagesUseCase extends BaseUseCase{

    public interface GetMovieImagesUseCaseCallback extends BaseUseCaseCallback {
        void onImagesUrlsLoaded(List<ImageEntity> backdrops, List<ImageEntity> posters);
    }

    private String apiKey;
    private int movieID;

    public GetMovieImagesUseCase(String apiKey, int movieID, GetMovieImagesUseCaseCallback callback) {
        super(callback);
        this.movieID = movieID;
        this.apiKey = apiKey;
    }

    @Override
    public void onRun() throws Throwable {
        API.http().movieImages(apiKey, movieID, new Callback<GetMovieImagesResponse>() {
            @Override
            public void success(GetMovieImagesResponse getMovieImagesResponse, Response response) {
                ((GetMovieImagesUseCaseCallback)callback).onImagesUrlsLoaded(getMovieImagesResponse.getBackdrops(), getMovieImagesResponse.getPosters());
            }

            @Override
            public void failure(RetrofitError error) {
                if (error.getKind() == RetrofitError.Kind.NETWORK) {
                    errorReason = NETWORK_ERROR;
                } else {
                    errorReason = error.getResponse().getReason();
                }
                onCancel();
            }
        });
    }
}
