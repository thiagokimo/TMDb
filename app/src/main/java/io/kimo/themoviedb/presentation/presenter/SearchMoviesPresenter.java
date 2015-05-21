package io.kimo.themoviedb.presentation.presenter;


import io.kimo.themoviedb.presentation.BasePresenter;
import io.kimo.themoviedb.presentation.view.SearchMoviesView;

public class SearchMoviesPresenter implements BasePresenter {

    private SearchMoviesView view;

    public SearchMoviesPresenter(SearchMoviesView view) {
        this.view = view;
    }

    @Override
    public void createView() {
        view.removeMoviesList();
    }

    @Override
    public void destroyView() {

    }
}
