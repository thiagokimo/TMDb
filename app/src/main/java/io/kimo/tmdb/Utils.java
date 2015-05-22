package io.kimo.tmdb;

import android.text.TextUtils;

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
            return "????";
        }

    }
}
