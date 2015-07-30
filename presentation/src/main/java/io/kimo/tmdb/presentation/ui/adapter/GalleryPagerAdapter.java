package io.kimo.tmdb.presentation.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import io.kimo.tmdb.presentation.mvp.model.ImageModel;
import io.kimo.tmdb.presentation.ui.fragment.GalleryFragment;

public class GalleryPagerAdapter extends FragmentStatePagerAdapter {

    public static final int BACKDROP_TAB = 0;
    public static final int POSTER_TAB = 1;

    private List<ImageModel> backdropUrls = new ArrayList<>();
    private List<ImageModel> posterUrls = new ArrayList<>();

    public GalleryPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(List<ImageModel> backdropUrls, List<ImageModel> posterUrls) {
        this.backdropUrls = backdropUrls;
        this.posterUrls = posterUrls;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case BACKDROP_TAB:
                return GalleryFragment.newInstance(backdropUrls);
            case POSTER_TAB:
                return GalleryFragment.newInstance(posterUrls);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case BACKDROP_TAB:
                return "backdrops";
            case POSTER_TAB:
                return "posters";
            default:
                return "";
        }
    }
}
