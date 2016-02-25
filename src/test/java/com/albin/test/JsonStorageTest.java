package com.albin.test;

import com.albin.api.MovieAlreadyExistsInDBException;
import com.albin.api.NoSuchMovieFoundException;
import com.albin.core.JsonStorage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by albinblent on 2016-02-10.
 */
public class JsonStorageTest {

    JsonStorage storage;

    @Before
    public void setUp(){
        storage = new JsonStorage();
    }

    @Test
    public void storeMovie_should_store_a_movie() throws MovieAlreadyExistsInDBException {
        //storage.storeMovie("Star warsd");
    }

    @Test
    public void getMovie_should_return_correct_movie() throws MovieAlreadyExistsInDBException, NoSuchMovieFoundException {
        //Assert.assertEquals("Could not get expected movie", "bond", storage.getMovie(1).getMovieName());
    }

    @Test
    public void getAllMovies_should_return_all_movies(){
    }

    @Test
    public void test_json_writer_and_reader(){
    }
}
