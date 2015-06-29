package io.kimo.tmdb.presentation.mvp.presenter;


import android.text.TextUtils;

import java.util.List;

import io.kimo.tmdb.BuildConfig;
import io.kimo.tmdb.domain.entity.ConfigurationEntity;
import io.kimo.tmdb.domain.entity.MovieEntity;
import io.kimo.tmdb.domain.usecase.GetImageConfigurationUseCase;
import io.kimo.tmdb.domain.usecase.SearchMovieUseCase;
import io.kimo.tmdb.presentation.TMDb;
import io.kimo.tmdb.presentation.mapper.MovieMapper;
import io.kimo.tmdb.presentation.mvp.BasePresenter;
import io.kimo.tmdb.presentation.mvp.view.SearchMoviesView;

public class SearchMoviesPresenter implements BasePresenter {

    private SearchMoviesView view;
    private String apiKey;
    private String lastQuery = "";

    public SearchMoviesPresenter(SearchMoviesView view) {
        this.view = view;
        this.apiKey = BuildConfig.TMDB_API_KEY;
    }

    @Override
    public void createView() {
        hideAllViews();
        view.showLoading();

        checkIfHasTheBaseImageURL();
    }

    @Override
    public void destroyView() {
        view.cleanTimer();
    }

    public void performSearch(String query) {
        if(!TextUtils.isEmpty(query) && !lastQuery.equals(query.trim())) { // avoid blank searches and consecutive repeated searches

            lastQuery = query.trim(); // store the last query

            view.hideView();
            view.showLoading();

            TMDb.JOB_MANAGER.addJobInBackground(new SearchMovieUseCase(apiKey, lastQuery, new SearchMovieUseCase.SearchMovieUseCaseCallback() {
                @Override
                public void onMoviesSearched(List<MovieEntity> movieEntities) {
                    view.hideLoading();
                    view.renderMoviesList(new MovieMapper().toModels(movieEntities));
                    view.showView();
                }

                @Override
                public void onError(String reason) {
                    view.hideLoading();
                    view.removeMoviesList();
                    view.showFeedback(reason);
                    lastQuery = "";
                }
            }));
        }
    }

    private void hideAllViews() {
        view.hideView();
        view.hideLoading();
    }

    private void checkIfHasTheBaseImageURL() {
        if(!TMDb.LOCAL_DATA.hasBaseImageURL()) {
            TMDb.JOB_MANAGER.addJobInBackground(new GetImageConfigurationUseCase(apiKey, new GetImageConfigurationUseCase.GetImageConfigurationUseCaseCallback() {
                @Override
                public void onConfigurationDownloaded(ConfigurationEntity configurationEntity) {
                    TMDb.LOCAL_DATA.storeBaseImageURL(configurationEntity.getBase_url());

                    showEmptyMovies();
                }

                @Override
                public void onError(String reason) {
                    view.hideLoading();
                    view.showView();
                    view.showFeedback(reason);
                }
            }));

        } else {
            showEmptyMovies();
        }
    }

    private void showEmptyMovies() {
        view.hideLoading();
        view.removeMoviesList();
        view.showView();
    }
}
