package com.albin.api;

import com.albin.core.MovieHandler;
import com.albin.core.MovieModel;
import org.apache.camel.Exchange;
import org.apache.camel.model.dataformat.JsonDataFormat;

/**
 * Created by albinblent on 2016-02-01.
 */
public class StoreMovieBean {

    private MovieHandler movieHandler;

    public StoreMovieBean(MovieHandler movieHandler) {
        this.movieHandler = movieHandler;
    }

    public void storeMovie(Exchange exchange) throws MovieAlreadyExistsInDBException {
        String newMovieName = exchange.getIn().getBody(MovieModel.class).getMovieName();
        movieHandler.storeMovie(newMovieName);
        System.out.println("Stored new movie: " + newMovieName + " to the movie DB");
    }
}
