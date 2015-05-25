package io.kimo.tmdb.presentation.mvp.view;

import io.kimo.tmdb.presentation.mvp.BaseView;

public interface LoadDataView extends BaseView {

    void showLoading();
    void hideLoading();

    void showRetry(String msg);
    void hideRetry();

    void showEmpty(String msg);
    void hideEmpty();
}
