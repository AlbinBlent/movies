package com.albin.api;

import com.albin.core.MovieModel;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by albinblent on 2016-02-02.
 */
public class MovieAlreadyExistsInDBProcessor implements Processor{
    @Override
    public void process(Exchange exchange) throws Exception {
        String newMovieName = exchange.getIn().getBody(MovieModel.class).getMovieName();
        System.err.println("Movie: " + newMovieName + " already exists in the storage");
        exchange.getOut().setBody("Movie already in storage");
        exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 404);
    }
}
