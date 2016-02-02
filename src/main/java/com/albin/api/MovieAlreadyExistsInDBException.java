package com.albin.api;

/**
 * Created by albinblent on 2016-02-01.
 */
public class MovieAlreadyExistsInDBException extends Exception {
    public MovieAlreadyExistsInDBException(String newMovieName){
        super("Movie: " + newMovieName + " already exists in the movie database");
    }
}
