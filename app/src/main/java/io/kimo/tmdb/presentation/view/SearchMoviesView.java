package io.kimo.tmdb.presentation.view;

import java.util.List;

import io.kimo.tmdb.presentation.model.MovieModel;

public interface SearchMoviesView extends LoadDataView {

    void renderMoviesList(List<MovieModel> movies);
    void removeMoviesList();

}
