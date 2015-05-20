package io.kimo.themoviedb.presentation.presenter;


import io.kimo.themoviedb.TheMovieDB;
import io.kimo.themoviedb.domain.usecase.SearchMovieUseCase;
import io.kimo.themoviedb.presentation.BasePresenter;
import io.kimo.themoviedb.presentation.view.SearchMoviesView;

public class SearchMoviesPresenter implements BasePresenter {

    private SearchMoviesView view;

    public SearchMoviesPresenter(SearchMoviesView view) {
        this.view = view;
    }

    @Override
    public void createView() {

        hideAllViews();
        view.showEmpty("Search for a movie.");

        TheMovieDB.JOB_MANAGER.addJobInBackground(new SearchMovieUseCase());
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
