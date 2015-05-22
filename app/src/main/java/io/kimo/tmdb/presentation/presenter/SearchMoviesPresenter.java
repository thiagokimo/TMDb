package io.kimo.tmdb.presentation.presenter;


import android.content.Context;
import android.text.TextUtils;

import java.util.List;

import io.kimo.tmdb.R;
import io.kimo.tmdb.TMDb;
import io.kimo.tmdb.domain.entity.MovieEntity;
import io.kimo.tmdb.domain.mapper.MovieMapper;
import io.kimo.tmdb.domain.usecase.SearchMovieUseCase;
import io.kimo.tmdb.presentation.BasePresenter;
import io.kimo.tmdb.presentation.view.SearchMoviesView;

public class SearchMoviesPresenter implements BasePresenter {

    private SearchMoviesView view;
    private Context context;

    private String lastQuery = "";

    public SearchMoviesPresenter(Context context, SearchMoviesView view) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void createView() {
        hideAllViews();
        view.removeMoviesList();
        view.showView();
    }

    @Override
    public void destroyView() {
        view.cleanTimer();
    }

    public void performSearch(String query) {
        if(!TextUtils.isEmpty(query) && !lastQuery.equals(query.trim())) { // avoid blank searches and consecutive repeated searches

            lastQuery = query.trim(); // store the last query

            view.hideView();
            view.showLoading();

            TMDb.JOB_MANAGER.addJobInBackground(new SearchMovieUseCase(context, context.getString(R.string.api_key), query.trim(), new SearchMovieUseCase.SearchMovieUseCaseCallback() {
                @Override
                public void onMoviesSearched(List<MovieEntity> movieEntities) {
                    view.hideLoading();
                    view.renderMoviesList(new MovieMapper().toModels(movieEntities));
                    view.showView();
                }

                @Override
                public void onError(String reason) {
                    view.hideLoading();
                    view.removeMoviesList();
                    view.showFeedback(reason);
                }
            }));
        } else {
            view.removeMoviesList();
        }
    }

    private void hideAllViews() {
        view.hideView();
        view.hideLoading();
    }
}
