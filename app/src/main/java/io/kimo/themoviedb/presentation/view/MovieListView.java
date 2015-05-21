package io.kimo.themoviedb.presentation.view;

import java.util.List;

import io.kimo.themoviedb.presentation.model.MovieModel;

public interface MovieListView extends LoadDataView {

    void renderMovies(List<MovieModel> movies);
    void clearMovies();

}
