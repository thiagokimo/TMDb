package io.kimo.themoviedb.presentation.view.ui.fragment;

import android.support.v7.widget.SearchView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.kimo.themoviedb.R;
import io.kimo.themoviedb.presentation.model.MovieModel;
import io.kimo.themoviedb.presentation.presenter.SearchMoviesPresenter;
import io.kimo.themoviedb.presentation.view.SearchMoviesView;
import io.kimo.themoviedb.presentation.view.ui.BaseFragment;

public class SearchMoviesFragment extends BaseFragment implements SearchMoviesView {

    private SearchView searchView;

    private SearchMoviesPresenter presenter;

    public static SearchMoviesFragment newInstance() {
        return new SearchMoviesFragment();
    }

    @Override
    public void instantiatePresenter() {
        presenter = new SearchMoviesPresenter(this);
    }

    @Override
    public void initializePresenter() {
        presenter.createView();
    }

    @Override
    public void finalizePresenter() {
        presenter.destroyView();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_search_movies;
    }

    @Override
    public void mapGUI(View view) {
        searchView = (SearchView) view.findViewById(R.id.search_view);
    }

    @Override
    public void configureGUI() {
        searchView.setIconifiedByDefault(false);
    }

    @Override
    public void showView() {}

    @Override
    public void hideView() {}

    @Override
    public void destroyItself() {}

    @Override
    public void renderMoviesList(List<MovieModel> movies) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.results_container, MovieListFragment.newInstance(movies))
                .commit();
    }

    @Override
    public void removeMoviesList() {
        renderMoviesList(new ArrayList<MovieModel>());
    }
}
