package com.albin.api;

import com.albin.core.MovieHandler;
import org.apache.camel.Exchange;

/**
 * Created by albinblent on 2016-02-02.
 */
public class GetAllMoviesBean {

    private MovieHandler movieHandler;

    public GetAllMoviesBean(MovieHandler movieHandler){
        this.movieHandler = movieHandler;
    }

    public void getAllMovies(Exchange exchange){
       exchange.getOut().setBody(movieHandler.getAllMovies());
        System.out.println("User fetched all movies");
    }
}
