package com.albin.core;

import com.albin.api.MovieAlreadyExistsInDBException;
import com.albin.api.NoSuchMovieFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by albinblent on 2016-02-10.
 */
public class JsonStorage implements IStore{

    @Override
    public void storeMovie(String newMovieTitle) throws MovieAlreadyExistsInDBException {

        if (movieAlreadyInStorage(newMovieTitle))
            throw new MovieAlreadyExistsInDBException(newMovieTitle);

        Map<Integer, MovieModel> movieMap = getContentOfJsonFile();

        Integer id = movieMap.size() + 1;

        MovieModel newMovie = new MovieModel(id, newMovieTitle);

        movieMap.put(id, newMovie);

        writeContentToJsonFile(movieMap);

    }

    @Override
    public MovieModel getMovie(int movieId) throws NoSuchMovieFoundException {
        return getContentOfJsonFile().get(movieId);
    }

    @Override
    public Collection<MovieModel> getAllMovies() {
        return null;
    }

    @Override
    public boolean movieAlreadyInStorage(String movieTitle) {

        Map<Integer, MovieModel> movieCollection = getContentOfJsonFile();

        for (Map.Entry<Integer, MovieModel>  movie :movieCollection.entrySet()) {
            if (movie.getValue().getMovieName().matches(movieTitle)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeMovie(int movieId) {

    }

    public void writeContentToJsonFile(Map<Integer, MovieModel> movieMap){
        JSONArray list = new JSONArray();

        for (Map.Entry<Integer, MovieModel>  movie :movieMap.entrySet()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", new Integer(movie.getValue().getId()));
            jsonObject.put("movieTitle", movie.getValue().getMovieName());
            list.add(jsonObject);
        }

        try {

            FileWriter file = new FileWriter("src/data/data.json");
            file.write(list.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, MovieModel> getContentOfJsonFile(){
        JSONParser parser = new JSONParser();

        Map<Integer, MovieModel> movieModelMap = new HashMap<>();

        try {

            Object obj = parser.parse(new FileReader("src/data/data.json"));

            JSONArray jsonArray = (JSONArray) obj;

            Iterator<JSONObject> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                JSONObject movie = iterator.next();
                MovieModel movieModel = new MovieModel((int) (long) movie.get("id"), (String) movie.get("movieTitle"));
                movieModelMap.put(movieModel.getId(), movieModel);
                System.out.println(movieModel.getId());
                System.out.println(movieModel.getMovieName());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return movieModelMap;
    }
}
