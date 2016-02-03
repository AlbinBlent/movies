package com.albin.test;

import com.albin.api.*;
import com.albin.core.InMemoryStorage;
import com.albin.core.MovieHandler;
import com.albin.core.MovieModel;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

/**
 * Created by albinblent on 2016-02-01.
 */
public class BeanTests {
    MovieHandler movieHandler;
    InMemoryStorage storageMock;

    @Before
    public void setUp() throws Exception {
        storageMock = Mockito.mock(InMemoryStorage.class);
        movieHandler = Mockito.mock(MovieHandler.class);
    }

    @Test
    public void getMovie_should_return_a_movie() throws NoSuchMovieFoundException {
        MovieBean getMovieBean = new MovieBean(movieHandler);
        CamelContext ctx = new DefaultCamelContext();
        Exchange exchange = new DefaultExchange(ctx);
        exchange.getIn().setHeader("id",1);
        getMovieBean.getMovie(exchange);
        verify(movieHandler, times(1)).getMovie(1);
    }

    @Test
    public void storeMovien_should_store_a_movie_to_the_DB() throws MovieAlreadyExistsInDBException {
        MovieBean storeMovieBean = new MovieBean(movieHandler);
        CamelContext ctx = new DefaultCamelContext();
        Exchange exchange = new DefaultExchange(ctx);
        MovieModel newMovie = new MovieModel(99, "Star Wars IV");
        exchange.getIn().setBody(newMovie);
        storeMovieBean.storeMovie(exchange);
        verify(movieHandler, times(1)).storeMovie("Star Wars IV");
    }

    @Test
    public void getAllMovies_should_return_all_movies_in_DB(){
        MovieBean getAllMoviesBean = new MovieBean(movieHandler);
        CamelContext ctx = new DefaultCamelContext();
        Exchange exchange = new DefaultExchange(ctx);
        getAllMoviesBean.getAllMovies(exchange);
        verify(movieHandler, times(1)).getAllMovies();

    }

    @Test
    public void removeMovie_should_remove_a_movie() throws NoSuchMovieFoundException {
        MovieBean moviesBean = new MovieBean(movieHandler);
        CamelContext ctx = new DefaultCamelContext();
        Exchange exchange = new DefaultExchange(ctx);
        exchange.getIn().setHeader("id",1);
        moviesBean.removeMovie(exchange);
        verify(movieHandler, times(1)).removeMovie(1);
    }
}
