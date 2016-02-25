package com.albin.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by albinblent on 2016-01-29.
 */
public class InMemoryStorage implements IStore {

    private static Map<Integer, MovieModel> movieCollection;

    public InMemoryStorage() {
        movieCollection = new HashMap<>();
    }

    public void storeMovie(String newMovieTitle) {
        MovieModel newMovie = new MovieModel(movieCollection.size() + 1, newMovieTitle);
        movieCollection.put(newMovie.getId(), newMovie);
    }

    public boolean movieAlreadyInStorage(String movieTitle){
        for (Map.Entry<Integer, MovieModel>  movie :movieCollection.entrySet()) {
            if (movie.getValue().getMovieName().matches(movieTitle)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeMovie(int movieId) {
       movieCollection.remove(movieId);
    }

    public MovieModel getMovie(int movieId) {
        return movieCollection.get(movieId);
    }

    public Collection<MovieModel> getAllMovies() {
        return movieCollection.values();
    }
}
