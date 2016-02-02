package com.albin.api;

/**
 * Created by albinblent on 2016-01-29.
 */

import com.albin.core.InMemoryStorage;
import com.albin.core.MovieHandler;
import com.albin.core.MovieModel;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class CamelRestRoute extends RouteBuilder{

    private static final String HOST = "0.0.0.0";
    private static final String PORT = "8080";
    private static InMemoryStorage inMemoryStorage;
    private static MovieHandler movieHandler;

    public CamelRestRoute(){
        inMemoryStorage = new InMemoryStorage();
        movieHandler = new MovieHandler(inMemoryStorage);

        // Add a test movie
        try {
            movieHandler.storeMovie("¡Los compadres grande!");
        } catch (MovieAlreadyExistsInDBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true")
                .host(HOST)
                .port(PORT);


        rest("/test/")
                .get("hi")
                .route()
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getOut().setBody("HI!");
                    }
                })
                .log("client called test/hi");

        // Movies api
        rest("/movies/")
                .consumes("application/json").produces("application/json")

                .get("get-movie/{id}").to("direct:retrieveMovie")
                .get("get-all-movies").to("direct:retrieveAllMovies")
                .post("add-movie").type(MovieModel.class).to("direct:storeMovie");

        from("direct:retrieveMovie")
                .log("client called retrieveMovie")
                .doTry()
                    .log("movie sent to client")
                    .bean(new GetMovieBean(movieHandler))
                .doCatch(NoSuchMovieFoundException.class)
                    .log("No such movie found exception raised")
                    .process(new Processor() {
                        @Override
                        public void process(Exchange exchange) throws Exception {
                            Integer id = exchange.getIn().getHeader("id", Integer.class);
                            System.err.println("Could not find movie with id: " + id);
                            exchange.getOut().setBody("Movie not found");
                            exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 404);
                        }
                    })
                .end();

        from("direct:storeMovie")
                .log("client called storeMovie")
                .doTry()
                    .log("movie stored to DB")
                    .bean(new StoreMovieBean(movieHandler))
                .doCatch(MovieAlreadyExistsInDBException.class)
                    .log("Movie already in storage")
                    .process(new Processor() {
                        @Override
                        public void process(Exchange exchange) throws Exception {
                            String newMovieName = exchange.getIn().getBody(MovieModel.class).getMovieName();
                            System.err.println("Movie: " + newMovieName + " already exists in the storage");
                            exchange.getOut().setBody("Movie already in storage");
                            exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 404);
                        }
                    })
                .end();

        from("direct:retrieveAllMovies")
                .log("client called retrieveAllMovies")
                .bean(new GetAllMoviesBean(movieHandler));
    }
}
