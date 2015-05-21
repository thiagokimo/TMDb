package io.kimo.themoviedb.presentation.presenter;


import java.util.List;

import io.kimo.themoviedb.R;
import io.kimo.themoviedb.TheMovieDB;
import io.kimo.themoviedb.domain.entity.MovieEntity;
import io.kimo.themoviedb.domain.mapper.MovieMapper;
import io.kimo.themoviedb.domain.usecase.SearchMovieUseCase;
import io.kimo.themoviedb.presentation.BasePresenter;
import io.kimo.themoviedb.presentation.model.MovieModel;
import io.kimo.themoviedb.presentation.view.MovieListView;

public class MovieListPresenter implements BasePresenter {

    private MovieListView view;
    private List<MovieModel> movies;

    public MovieListPresenter(MovieListView view, List<MovieModel> movies) {
        this.view = view;
        this.movies = movies;
    }

    @Override
    public void createView() {

        hideAllViews();

        view.showLoading();

        if(movies.isEmpty()) {
            view.showEmpty("There are no movies to show");
            view.hideLoading();
        } else {
            view.renderMovies(movies);
            view.hideLoading();
            view.showView();
        }
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
