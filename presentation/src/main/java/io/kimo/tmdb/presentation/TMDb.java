package io.kimo.tmdb.presentation;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.squareup.picasso.Picasso;

import io.fabric.sdk.android.Fabric;
import io.kimo.tmdb.presentation.data.PicassoCache;
import io.kimo.tmdb.presentation.data.SharedPreferencesController;

public class TMDb extends Application {

    public static JobManager JOB_MANAGER;
    public static Picasso PICASSO;
    public static SharedPreferencesController LOCAL_DATA;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        configureJobManager();
        configurePicasso();
        configureDataLayer();
    }

    private void configureJobManager() {
        JOB_MANAGER = new JobManager(this, new Configuration.Builder(this)
                .minConsumerCount(1)
                .maxConsumerCount(3)
                .loadFactor(3)
                .build());
    }

    private void configurePicasso() {
        PICASSO = PicassoCache.INSTANCE.getPicassoCache(this);
    }

    private void configureDataLayer() {
        LOCAL_DATA = new SharedPreferencesController(this);
    }
}
