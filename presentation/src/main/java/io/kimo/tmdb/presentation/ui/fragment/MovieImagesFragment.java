package io.kimo.tmdb.presentation.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.List;

import io.kimo.tmdb.R;
import io.kimo.tmdb.presentation.Utils;
import io.kimo.tmdb.presentation.mvp.model.ImageModel;
import io.kimo.tmdb.presentation.mvp.presenter.MovieImagesPresenter;
import io.kimo.tmdb.presentation.mvp.view.MovieImagesView;
import io.kimo.tmdb.presentation.ui.BaseActivity;
import io.kimo.tmdb.presentation.ui.BaseFragment;
import io.kimo.tmdb.presentation.ui.adapter.GalleryPagerAdapter;

public class MovieImagesFragment extends BaseFragment implements MovieImagesView {

    public static final String TAG = MovieImagesFragment.class.getSimpleName();
    public static final String MOVIE_ID = TAG + ".MOVIE_ID";

    private View mainView, loadingView, retryView;
    private TextView retryMessage;
    private Button retryButton;

    private ViewPager viewPager;
    private GalleryPagerAdapter adapter;
    private SmartTabLayout slidingTabLayout;

    private MovieImagesPresenter presenter;

    private int movieID;

    public static MovieImagesFragment newInstance(int movieID) {
        MovieImagesFragment fragment = new MovieImagesFragment();

        Bundle args = new Bundle();
        args.putInt(MOVIE_ID, movieID);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        Bundle args = getArguments();

        if(args != null) {
            movieID = args.getInt(MOVIE_ID);
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public void instantiatePresenter() {
        presenter = new MovieImagesPresenter(this, movieID);
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
        return R.layout.fragment_pager_with_tabs;
    }

    @Override
    public void mapGUI(View view) {
        mainView = view.findViewById(R.id.main_container);
        loadingView = view.findViewById(R.id.view_loading);
        retryView = view.findViewById(R.id.view_retry);
        retryMessage = (TextView) retryView.findViewById(R.id.text);
        retryButton = (Button) retryView.findViewById(R.id.button);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        slidingTabLayout = (SmartTabLayout) view.findViewById(R.id.tabs);
    }

    @Override
    public void configureGUI() {
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createView();
            }
        });

        adapter = new GalleryPagerAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);
        slidingTabLayout.setViewPager(viewPager);

        //REMOVE TOOLBAR ELEVATION ON LOLLIPOP
        if(Utils.isLollipop()) {
            ((BaseActivity)getActivity()).getToolbar().setElevation(0);
        }
    }

    @Override
    public void renderTabs(List<ImageModel> backdrops, List<ImageModel> posters) {
        adapter.setData(backdrops, posters);
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
    public void showEmpty(String msg) {}

    @Override
    public void hideEmpty() {}

    @Override
    public void showView() {
        mainView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideView() {
        mainView.setVisibility(View.GONE);
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
