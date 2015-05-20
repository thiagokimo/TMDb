package io.kimo.themoviedb;

public class Utils {

    public static String buildCompleteImageURL(String path) {
        if(TheMovieDB.LOCAL_DATA.hasBaseImageURL()) {
            return TheMovieDB.LOCAL_DATA.getBaseImageURL() + "original/" + path;
        } else {
            return path;
        }
    }
}
