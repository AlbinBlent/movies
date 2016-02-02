package com.albin.core;

import com.albin.api.MovieAlreadyExistsInDBException;
import com.albin.api.NoSuchMovieFoundException;

import java.util.Collection;

/**
 * Created by albinblent on 2016-01-29.
 */
public class MovieHandler {

    IStore storage;

    public MovieHandler(IStore storage){

        this.storage = storage;

    }

    public void storeMovie(String newMovieTitle) throws MovieAlreadyExistsInDBException {

        if (storage.movieAlreadyInStorage(newMovieTitle)) {
            throw new MovieAlreadyExistsInDBException(newMovieTitle);
        }

        storage.storeMovie(newMovieTitle);
    }

    public MovieModel getMovie(Integer id) throws NoSuchMovieFoundException {
        if (storage.getMovie(id) == null)
            throw new NoSuchMovieFoundException(id);
        return storage.getMovie(id);
    }

    public Collection<MovieModel> getAllMovies(){
        return storage.getAllMovies();
    }
}
