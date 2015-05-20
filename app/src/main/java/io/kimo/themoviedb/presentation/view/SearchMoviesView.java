package io.kimo.themoviedb.presentation.view;

import java.util.List;

import io.kimo.themoviedb.presentation.model.MovieModel;

public interface SearchMoviesView extends LoadDataView {

    void renderMovies(List<MovieModel> movies);
    void clearMovies();

}
