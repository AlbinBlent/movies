package com.albin.core;

/**
 * Created by albinblent on 2016-01-29.
 */
public class MovieModel {

    private int id;
    private String movieName;

    public MovieModel(int id, String movieName){
        this.id = id;
        this.movieName = movieName;
    }

    // Dummy constructor for JSON parsing
    public MovieModel(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
