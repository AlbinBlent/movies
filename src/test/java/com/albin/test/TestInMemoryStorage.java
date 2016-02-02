package com.albin.test;

import com.albin.api.MovieAlreadyExistsInDBException;
import com.albin.core.InMemoryStorage;
import com.albin.core.MovieModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by albinblent on 2016-01-29.
 */
public class TestInMemoryStorage {

   /*
   Det här är ju databasen... Borde jag ens testa den med enhetstester?
   Jag menar man brukar ju mocka bort databaser
    */

   InMemoryStorage storage;

   @Before
   public void setUp(){
      storage = new  InMemoryStorage();
   }

   @Test
   public void storeMovie_should_store_a_movie(){

      MovieModel movie = new MovieModel(1,"Bond");
      //storage.storeMovie(movie);
      Assert.assertEquals("movie was not stored correctly", 1, 1);
      //hur ska jag testa att filmen har blivit tillagd? Om jag skriver en funktion
      // tex getSize så testar jag ju två funktioner med ett test.

   }

   @Test
   public void getMovie_should_return_one_movie() throws MovieAlreadyExistsInDBException {
      //MovieModel movie = new MovieModel(1,"Bond");
      storage.storeMovie("bond");
      Assert.assertEquals("Unable to fetch movie", "bond",storage.getMovie(1).getMovieName());
      //detta test testar ju också två funktioner
   }

   @Test
   public void getAllMovies_should_return_all_movies_in_storage() throws MovieAlreadyExistsInDBException {
      storage.storeMovie("Bond");
      storage.storeMovie("Toy Story");
      Assert.assertEquals("getAllMovies did not return the right amount of movies", 2, storage.getAllMovies().size());

   }

   @Test
   public void movieAlreadyInStorage_returns_true_if_checking_for_a_movie_that_is_already_storage_and_false_if_it_is_not() throws MovieAlreadyExistsInDBException {
      storage.storeMovie("Kalle Anka");
      boolean booleanThatShouldBeTrue = storage.movieAlreadyInStorage("Kalle Anka");
      boolean booleanThatShouldBeFalse = storage.movieAlreadyInStorage("Gone in 60 seconds");

      Assert.assertTrue("Storage could not detect that this move already was in storage", booleanThatShouldBeTrue);
      Assert.assertFalse("Storage could not detect that this move was not in storage", booleanThatShouldBeFalse);

   }

}
