package io.kimo.themoviedb.presentation.view;

import io.kimo.themoviedb.presentation.BaseView;

public interface LoadDataView extends BaseView {

    void showLoading();
    void hideLoading();

    void showRetry(String msg);
    void hideRetry();

    void showEmpty(String msg);
    void hideEmpty();
}
