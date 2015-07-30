package io.kimo.tmdb.presentation.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;

import io.kimo.tmdb.R;
import io.kimo.tmdb.presentation.TMDb;
import io.kimo.tmdb.presentation.Utils;
import io.kimo.tmdb.presentation.mvp.presenter.LoadingImagePresenter;
import io.kimo.tmdb.presentation.mvp.view.LoadingImageView;
import io.kimo.tmdb.presentation.ui.BaseFragment;

public class LoadingImageFragment extends BaseFragment implements LoadingImageView {

    public static final String TAG = LoadingImageFragment.class.getSimpleName();
    public static final String URL = TAG + ".URL";

    private ImageView mainView;
    private View loadingView, retryView;
    private TextView retryFeedback;
    private Button retryButton;

    private LoadingImagePresenter presenter;

    private String url;

    public static LoadingImageFragment newInstance(String url) {
        LoadingImageFragment fragment = new LoadingImageFragment();

        Bundle args = new Bundle();
        args.putString(URL, url);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        Bundle args = getArguments();

        if(args != null) {
            url = args.getString(URL);
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public void instantiatePresenter() {
        presenter = new LoadingImagePresenter(this, url);
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
        return R.layout.fragment_loading_image;
    }

    @Override
    public void mapGUI(View view) {
        mainView = (ImageView) view.findViewById(R.id.image);
        loadingView = view.findViewById(R.id.view_loading);
        retryView = view.findViewById(R.id.view_retry);
        retryFeedback = (TextView) retryView.findViewById(R.id.text);
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
    }

    @Override
    public void renderImage(String url) {

        TMDb.PICASSO.load(url).into(mainView, new Callback() {
            @Override
            public void onSuccess() {
                presenter.onImageRendered();
                Utils.hideSystemUI(getActivity());
            }

            @Override
            public void onError() {
                presenter.onRenderingError();
            }
        });
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
        retryFeedback.setText(msg);
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
