package io.kimo.tmdb.presentation;

import android.os.Build;
import android.text.TextUtils;

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
}
