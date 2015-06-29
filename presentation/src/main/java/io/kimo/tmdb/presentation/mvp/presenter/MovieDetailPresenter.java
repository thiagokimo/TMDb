package io.kimo.tmdb.presentation.mvp.presenter;

import android.text.TextUtils;

import io.kimo.tmdb.BuildConfig;
import io.kimo.tmdb.domain.entity.MovieDetailEntity;
import io.kimo.tmdb.domain.usecase.GetMovieDetailUseCase;
import io.kimo.tmdb.presentation.TMDb;
import io.kimo.tmdb.presentation.mapper.MovieMapper;
import io.kimo.tmdb.presentation.mvp.BasePresenter;
import io.kimo.tmdb.presentation.mvp.model.MovieModel;
import io.kimo.tmdb.presentation.mvp.view.MovieDetailView;

public class MovieDetailPresenter implements BasePresenter {

    private MovieDetailView view;
    private MovieModel movieModel;
    private String apiKey;

    public MovieDetailPresenter(MovieDetailView view, MovieModel movieModel) {
        this.view = view;
        this.movieModel = movieModel;
        this.apiKey = BuildConfig.TMDB_API_KEY;
    }

    @Override
    public void createView() {
        hideAllViews();
        view.showLoading();

        downloadMovieDetails();
    }

    @Override
    public void destroyView() {}

    public void onHomepageClicked() {
        if(!TextUtils.isEmpty(movieModel.getHomepage())) {
            view.openMovieWebsite(movieModel.getHomepage());
        }
    }

    public void onGalleryClicked() {
        view.openGallery();
    }

    public void onMainViewScrolled() {
        view.updateToolbarColor();
    }

    private void hideAllViews() {
        view.hideView();
        view.hideLoading();
        view.hideRetry();
        view.hideEmpty();
    }

    private void downloadMovieDetails() {
        TMDb.JOB_MANAGER.addJobInBackground(new GetMovieDetailUseCase(apiKey, movieModel.getId(), new GetMovieDetailUseCase.GetMovieDetailUseCaseCallback() {
            @Override
            public void onMovieDetailLoaded(MovieDetailEntity movieDetailEntity) {
                updateMovieModel(movieDetailEntity);

                view.updateBackground(movieModel.getBigCover());
                view.updateTitle(movieModel.getName());

                if (TextUtils.isEmpty(movieModel.getYearOfRelease())) {
                    view.hideYearOfRelease();
                } else {
                    view.updateYearOfRelease(movieModel.getYearOfRelease());
                }

                if (TextUtils.isEmpty(movieModel.getHomepage())) {
                    view.hideHomepage();
                } else {
                    view.updateHomepage(movieModel.getHomepage());
                }

                if (movieModel.getCompanies().isEmpty()) {
                    view.hideCompanies();
                } else {
                    view.updateCompanies(movieModel.getCompanies());
                }

                if (TextUtils.isEmpty(movieModel.getTagline())) {
                    view.hideTagline();
                } else {
                    view.updateTagline(movieModel.getTagline());
                }

                if (TextUtils.isEmpty(movieModel.getOverview())) {
                    view.hideOverview();
                } else {
                    view.updateOverview(movieModel.getOverview());
                }

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

    private void updateMovieModel(MovieDetailEntity detailEntity) {
        movieModel = new MovieMapper().addDetails(movieModel, detailEntity);
    }
}
