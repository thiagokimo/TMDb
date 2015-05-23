package io.kimo.tmdb.presentation;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.DisplayMetrics;

public class Utils {

    public static String buildCompleteImageURL(String path) {
        if(TMDb.LOCAL_DATA.hasBaseImageURL()) {
            return TMDb.LOCAL_DATA.getBaseImageURL() + "original/" + path;
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

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }
}
