package io.kimo.tmdb.presentation.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.kimo.tmdb.R;
import io.kimo.tmdb.presentation.mapper.ImageMapper;
import io.kimo.tmdb.presentation.mvp.model.ImageModel;
import io.kimo.tmdb.presentation.mvp.presenter.GalleryPresenter;
import io.kimo.tmdb.presentation.mvp.view.GalleryView;
import io.kimo.tmdb.presentation.ui.BaseFragment;
import io.kimo.tmdb.presentation.ui.adapter.ImageAdapter;

public class GalleryFragment extends BaseFragment implements GalleryView {

    public static final String TAG = GalleryFragment.class.getSimpleName();
    public static final String IMAGES = TAG + ".IMAGES";

    private RecyclerView recyclerView;
    private View loadingView, retryView, emptyView;
    private TextView retryMessage, emptyMessage;
    private Button retryButton;

    private ImageAdapter adapter;

    private GalleryPresenter presenter;

    private List<ImageModel> urls;

    public static GalleryFragment newInstance(List<ImageModel> urls) {
        GalleryFragment fragment = new GalleryFragment();

        Bundle args = new Bundle();
        args.putStringArrayList(IMAGES, (ArrayList<String>) new ImageMapper("").serializeModels(urls));

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        Bundle args = getArguments();

        if(args != null) {
            urls = new ImageMapper("").deserializeModels(args.getStringArrayList(IMAGES));
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public void instantiatePresenter() {
        presenter = new GalleryPresenter(this, urls);
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
        emptyMessage = (TextView) emptyView.findViewById(R.id.text);
        retryView = view.findViewById(R.id.view_retry);
        retryMessage = (TextView) retryView.findViewById(R.id.text);
        retryButton = (Button) retryView.findViewById(R.id.button);
    }

    @Override
    public void configureGUI() {
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createView();
            }
        });

        //RECYCLER CONFIGURATIONS
        adapter = new ImageAdapter(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);
        //RECYCLER CONFIGURATIONS
    }

    @Override
    public void renderImages(List<ImageModel> images) {
        adapter.setData(urls);
    }

    @Override
    public void clearImages() {
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
        retryMessage.setText(msg);
        retryView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        retryView.setVisibility(View.GONE);
    }

    @Override
    public void showEmpty(String msg) {
        emptyMessage.setText(msg);
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
    public void showFeedback(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void destroyItself() {
        getActivity().finish();
    }
}
