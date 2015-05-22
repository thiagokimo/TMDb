package io.kimo.tmdb;

import android.app.Application;

import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.squareup.picasso.Picasso;

import io.kimo.tmdb.data.PicassoCache;
import io.kimo.tmdb.data.SharedPreferencesController;

public class TMDb extends Application {

    public static JobManager JOB_MANAGER;
    public static Picasso PICASSO;
    public static SharedPreferencesController LOCAL_DATA;

    @Override
    public void onCreate() {
        super.onCreate();

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
