package io.kimo.tmdb.presentation.view;

import java.util.List;

import io.kimo.tmdb.presentation.model.MovieModel;

public interface MovieListView extends LoadDataView {

    void renderMovies(List<MovieModel> movies);
    void clearMovies();

}
