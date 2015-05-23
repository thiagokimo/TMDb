package io.kimo.tmdb.presentation.mvp.presenter;

import android.content.Context;

import java.util.List;

import io.kimo.tmdb.R;
import io.kimo.tmdb.domain.entity.ImageEntity;
import io.kimo.tmdb.domain.usecase.GetMovieImagesUseCase;
import io.kimo.tmdb.presentation.TMDb;
import io.kimo.tmdb.presentation.mapper.ImageMapper;
import io.kimo.tmdb.presentation.mvp.BasePresenter;
import io.kimo.tmdb.presentation.mvp.view.MovieImagesView;

public class MovieImagesPresenter implements BasePresenter {

    private Context context;
    private MovieImagesView view;
    private int movieID;

    public MovieImagesPresenter(MovieImagesView view, Context context, int movieID) {
        this.movieID = movieID;
        this.view = view;
        this.context = context;
    }

    @Override
    public void createView() {
        hideAllViews();

        view.showLoading();

        TMDb.JOB_MANAGER.addJobInBackground(new GetMovieImagesUseCase(context.getString(R.string.api_key), movieID, new GetMovieImagesUseCase.GetMovieImagesUseCaseCallback() {
            @Override
            public void onImagesUrlsLoaded(List<ImageEntity> backdrops, List<ImageEntity> posters) {
                view.renderTabs(new ImageMapper("w300").toModels(backdrops), new ImageMapper("w185").toModels(posters));
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

    @Override
    public void destroyView() {}

    private void hideAllViews() {
        view.hideView();
        view.hideEmpty();
        view.hideLoading();
        view.hideRetry();
    }
}
