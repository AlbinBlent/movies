package com.albin.api;

import com.albin.core.MovieHandler;
import com.albin.core.MovieModel;
import org.apache.camel.Exchange;

/**
 * Created by albinblent on 2016-02-03.
 */
public class MovieBean {

    private MovieHandler movieHandler;

    public MovieBean(MovieHandler movieHandler){
        this.movieHandler = movieHandler;
    }

    public void getAllMovies(Exchange exchange){
       exchange.getOut().setBody(movieHandler.getAllMovies());
    }
    public void getMovie(Exchange exchange) throws NoSuchMovieFoundException {
        Integer id = exchange.getIn().getHeader("id", Integer.class);
        exchange.getIn().setBody(movieHandler.getMovie(id));
        System.out.println("Client retrieved movie with id: " + id);
    }
    public void storeMovie(Exchange exchange) throws MovieAlreadyExistsInDBException {
        String newMovieName = exchange.getIn().getBody(MovieModel.class).getMovieName();
        movieHandler.storeMovie(newMovieName);
        System.out.println("Client stored movie: " + newMovieName);
    }
    public void removeMovie(Exchange exchange) throws NoSuchMovieFoundException {
        int id = exchange.getIn().getHeader("id", Integer.class);
        movieHandler.removeMovie(id);
        System.out.println("Client deleted movie with id:" + id);
    }
}
