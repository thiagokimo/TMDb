package io.kimo.themoviedb.presentation.view;

import java.util.List;

import io.kimo.themoviedb.presentation.BaseView;
import io.kimo.themoviedb.presentation.model.MovieModel;

public interface SearchMoviesView extends BaseView {

    void renderMoviesList(List<MovieModel> movies);
    void removeMoviesList();

}
