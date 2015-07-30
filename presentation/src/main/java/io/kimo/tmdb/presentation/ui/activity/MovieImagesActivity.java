package io.kimo.tmdb.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import io.kimo.tmdb.R;
import io.kimo.tmdb.presentation.ui.BaseActivity;
import io.kimo.tmdb.presentation.ui.fragment.MovieImagesFragment;

public class MovieImagesActivity extends BaseActivity {

    public static final String TAG = MovieImagesActivity.class.getSimpleName();
    public static final String MOVIE_ID = TAG + ".MOVIE_ID";

    private int movieID;

    public static Intent getCallingIntent(Context context, int movieID) {
        Intent intentToBeCalled = new Intent(context, MovieImagesActivity.class);
        intentToBeCalled.putExtra(MOVIE_ID, movieID);

        return intentToBeCalled;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle args = getIntent().getExtras();

        if(args != null) {
            movieID = args.getInt(MOVIE_ID);
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_with_toolbar;
    }

    @Override
    public Fragment getMainFragment() {
        return MovieImagesFragment.newInstance(movieID);
    }
}
