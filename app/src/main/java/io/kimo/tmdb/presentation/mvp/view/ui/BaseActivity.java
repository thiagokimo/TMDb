package io.kimo.tmdb.presentation.mvp.view.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import io.kimo.tmdb.R;


public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        configureToolbar();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, getMainFragment())
                .commit();
    }

    private void configureToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public abstract int getLayoutResource();
    public abstract Fragment getMainFragment();
    public Toolbar getToolbar() {
        return toolbar;
    }
}
