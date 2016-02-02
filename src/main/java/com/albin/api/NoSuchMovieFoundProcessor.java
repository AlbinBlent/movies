package com.albin.api;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by albinblent on 2016-02-02.
 */
public class NoSuchMovieFoundProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = exchange.getIn().getHeader("id", Integer.class);
        System.err.println("Could not find movie with id: " + id);
        exchange.getOut().setBody("Movie not found");
        exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 404);
    }
}
