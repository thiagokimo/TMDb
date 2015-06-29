package io.kimo.tmdb.presentation.mvp.presenter;

import java.util.List;

import io.kimo.tmdb.BuildConfig;
import io.kimo.tmdb.domain.entity.ImageEntity;
import io.kimo.tmdb.domain.usecase.GetMovieImagesUseCase;
import io.kimo.tmdb.presentation.TMDb;
import io.kimo.tmdb.presentation.mapper.ImageMapper;
import io.kimo.tmdb.presentation.mvp.BasePresenter;
import io.kimo.tmdb.presentation.mvp.view.MovieImagesView;

public class MovieImagesPresenter implements BasePresenter {

    private MovieImagesView view;
    private int movieID;

    public MovieImagesPresenter(MovieImagesView view, int movieID) {
        this.movieID = movieID;
        this.view = view;
    }

    @Override
    public void createView() {
        hideAllViews();

        view.showLoading();

        downloadMovieImages();
    }

    @Override
    public void destroyView() {}

    private void hideAllViews() {
        view.hideView();
        view.hideEmpty();
        view.hideLoading();
        view.hideRetry();
    }

    private void downloadMovieImages() {
        TMDb.JOB_MANAGER.addJobInBackground(new GetMovieImagesUseCase(BuildConfig.TMDB_API_KEY, movieID, new GetMovieImagesUseCase.GetMovieImagesUseCaseCallback() {
            @Override
            public void onImagesUrlsLoaded(List<ImageEntity> backdrops, List<ImageEntity> posters) {
                view.renderTabs(new ImageMapper("w780").toModels(backdrops), new ImageMapper("w500").toModels(posters));
                view.hideLoading();
                view.showView();
            }

            @Override
            public void onError(String reason) {
                view.showFeedback(reason);
                view.destroyItself();
            }
        }));
    }
}
