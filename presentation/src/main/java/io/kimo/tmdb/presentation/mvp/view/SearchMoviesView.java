package io.kimo.tmdb.presentation.mvp.view;

import java.util.List;

import io.kimo.tmdb.presentation.mvp.model.MovieModel;

public interface SearchMoviesView extends LoadDataView {

    void renderMoviesList(List<MovieModel> movies);
    void removeMoviesList();

    void cleanTimer();
}
