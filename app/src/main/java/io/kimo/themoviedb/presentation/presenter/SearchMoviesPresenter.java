package io.kimo.themoviedb.presentation.presenter;


import android.content.Context;

import java.util.List;

import io.kimo.themoviedb.R;
import io.kimo.themoviedb.TheMovieDB;
import io.kimo.themoviedb.domain.entity.MovieEntity;
import io.kimo.themoviedb.domain.mapper.MovieMapper;
import io.kimo.themoviedb.domain.usecase.SearchMovieUseCase;
import io.kimo.themoviedb.presentation.BasePresenter;
import io.kimo.themoviedb.presentation.model.MovieModel;
import io.kimo.themoviedb.presentation.view.SearchMoviesView;

public class SearchMoviesPresenter implements BasePresenter {

    private SearchMoviesView view;
    private Context context;

    public SearchMoviesPresenter(Context context, SearchMoviesView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void createView() {

        hideAllViews();
        view.showEmpty("Search for a movie.");

        TheMovieDB.JOB_MANAGER.addJobInBackground(new SearchMovieUseCase(context, context.getString(R.string.api_key), "Mad Max", new SearchMovieUseCase.SearchMovieUseCaseCallback() {
            @Override
            public void onMoviesSearched(List<MovieEntity> movieEntities) {
                if(movieEntities.isEmpty()) {
                    view.hideLoading();
                    view.showEmpty("No movies were found.");
                } else {
                    List<MovieModel> movieModels = new MovieMapper().toModels(movieEntities);
                    view.renderMovies(movieModels);
                    view.hideLoading();
                    view.showView();
                }
            }

            @Override
            public void onError(String reason) {
                view.hideLoading();
                view.showRetry(reason);
            }
        }));
    }

    @Override
    public void destroyView() {

    }

    private void hideAllViews() {
        view.hideView();
        view.hideEmpty();
        view.hideLoading();
        view.hideRetry();
    }
}
