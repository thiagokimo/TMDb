package io.kimo.tmdb.presentation.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        instantiatePresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(getLayoutResource(), container, false);
        mapGUI(view);
        configureGUI();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializePresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        finalizePresenter();
    }

    public abstract void instantiatePresenter();
    public abstract void initializePresenter();
    public abstract void finalizePresenter();

    public abstract int getLayoutResource();

    public abstract void mapGUI(View view);
    public abstract void configureGUI();
}
