package io.kimo.tmdb.presentation.mvp.view;

public interface MovieDetailView extends LoadDataView {

    void updateImage(String value);
    void updateTitle(String value);
}
