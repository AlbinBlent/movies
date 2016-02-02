package com.albin.api;

/**
 * Created by albinblent on 2016-02-01.
 */
public class NoSuchMovieFoundException extends Exception {
    public NoSuchMovieFoundException(Integer movieId){
        super("Could not find movie with id: " + movieId);
    }
}
