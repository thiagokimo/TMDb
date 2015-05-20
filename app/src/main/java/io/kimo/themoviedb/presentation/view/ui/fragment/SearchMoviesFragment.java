package io.kimo.themoviedb.presentation.view.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import io.kimo.themoviedb.R;
import io.kimo.themoviedb.presentation.model.MovieModel;
import io.kimo.themoviedb.presentation.presenter.SearchMoviesPresenter;
import io.kimo.themoviedb.presentation.view.SearchMoviesView;
import io.kimo.themoviedb.presentation.view.ui.BaseFragment;
import io.kimo.themoviedb.presentation.view.ui.adapter.SearchMoviesAdapter;

public class SearchMoviesFragment extends BaseFragment implements SearchMoviesView {

    private RecyclerView recyclerView;
    private View loadingView, retryView, emptyView;
    private TextView retryMsg, emptyMsg;
    private Button retryButton;

    private SearchMoviesAdapter adapter;

    private SearchMoviesPresenter presenter;

    public static SearchMoviesFragment newInstance() {
        return new SearchMoviesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_search, menu);
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
        return R.layout.fragment_recycler;
    }

    @Override
    public void mapGUI(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        loadingView = view.findViewById(R.id.view_loading);
        emptyView = view.findViewById(R.id.view_empty);
        retryView = view.findViewById(R.id.view_retry);

        emptyMsg = (TextView) emptyView.findViewById(R.id.text);
        retryMsg = (TextView) retryView.findViewById(R.id.text);

        retryButton = (Button) retryView.findViewById(R.id.button);
    }

    @Override
    public void configureGUI() {

        //RECYLCER VIEW CONFIGURATIONS
        adapter = new SearchMoviesAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        //RECYLCER VIEW CONFIGURATIONS


        //RETRY BUTTON CONFIGURATIONS
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createView();
            }
        });
        //RETRY BUTTON CONFIGURATIONS

    }

    @Override
    public void renderMovies(List<MovieModel> movies) {
        adapter.setData(movies);
    }

    @Override
    public void clearMovies() {
        adapter.clearData();
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void showRetry(String msg) {
        retryMsg.setText(msg);
        retryView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        retryView.setVisibility(View.GONE);
    }

    @Override
    public void showEmpty(String msg) {
        emptyMsg.setText(msg);
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmpty() {
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public void showView() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideView() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void destroyItself() {
        getActivity().finish();
    }
}
