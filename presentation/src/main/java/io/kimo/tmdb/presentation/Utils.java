package io.kimo.tmdb.presentation;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import io.kimo.tmdb.R;
import io.kimo.tmdb.presentation.ui.BaseActivity;

public class Utils {

    public static String buildCompleteImageURL(String path, String size) {
        if(TMDb.LOCAL_DATA.hasBaseImageURL()) {
            return TMDb.LOCAL_DATA.getBaseImageURL() + size + path;
        } else {
            return path;
        }
    }

    public static String getYearFromServerDate(String serverDate) {
        if(!TextUtils.isEmpty(serverDate)) {
            return serverDate.substring(0,4);
        } else {
            return "";
        }
    }

    public static boolean isLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static int getAlphaColor(int color, float scrollRatio) {
        return Color.argb((int) (scrollRatio * 255f), Color.red(color), Color.green(color), Color.blue(color));
    }

    public static void restoreToolbarColor(BaseActivity activity, Toolbar toolbar) {
        if(toolbar != null && activity != null) {
            ColorDrawable toolbarBackground = (ColorDrawable) toolbar.getBackground();
            toolbarBackground.setColor(activity.getResources().getColor(R.color.primary));
            activity.getSupportActionBar().setBackgroundDrawable(toolbarBackground);
        }
    }

    public static void setContentBehindToolbar(BaseActivity activity) {
        activity.removeContentPaddingTop();
    }

    public static void hideSystemUI(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}
