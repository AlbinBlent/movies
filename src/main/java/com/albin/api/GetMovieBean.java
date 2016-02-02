package com.albin.api;

import com.albin.core.MovieHandler;
import org.apache.camel.Exchange;

/**
 * Created by albinblent on 2016-02-01.
 */
public class GetMovieBean {

    private MovieHandler movieHandler;

    public GetMovieBean(MovieHandler movieHandler){
        this.movieHandler = movieHandler;
    }

    public void getMovie(Exchange exchange) throws NoSuchMovieFoundException {
        Integer id = exchange.getIn().getHeader("id", Integer.class);
        exchange.getIn().setBody(movieHandler.getMovie(id));
        System.out.println("User fetching movie with id: " + id);
        //exchange.getIn().setBody("test movie");
    }
}
