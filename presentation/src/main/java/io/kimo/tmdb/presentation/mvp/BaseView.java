package io.kimo.tmdb.presentation.mvp;

public interface BaseView {

    void showView();
    void hideView();

    void showFeedback(String msg);
    void destroyItself();
}
