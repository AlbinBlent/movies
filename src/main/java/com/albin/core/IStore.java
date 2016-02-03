package com.albin.core;

import com.albin.api.MovieAlreadyExistsInDBException;

import java.util.Collection;

/**
 * Created by albinblent on 2016-01-29.
 */
public interface IStore {
    public void storeMovie(String newMovieTitle) throws MovieAlreadyExistsInDBException;
    public MovieModel getMovie(int movieId);
    public Collection<MovieModel> getAllMovies();
    public boolean movieAlreadyInStorage(String movieTitle);
    public void removeMovie(int movieId);
}
