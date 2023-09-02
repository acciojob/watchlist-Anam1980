package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    Map<String, Movie> movies = new HashMap<>();
    Map<String, Director> directors = new HashMap<>();
    Map<String, List<String>> directorMovieMap = new HashMap<>();

    public void addMovie(Movie movie) {
        movies.put(movie.getName(), movie);
    }

    public void addDirector(Director director) {
        directors.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movieName, String directorName) {
        if(directors.containsKey(directorName) && movies.containsKey(movieName)){
            directors.put(directorName,directors.get(directorName));
            movies.put(movieName, movies.get(movieName));

            List<String>currentMovies = new ArrayList<>();
            if(directorMovieMap.containsKey(directorName)){
                currentMovies = directorMovieMap.get(directorName);
            }
            currentMovies.add(movieName);
            directorMovieMap.put(directorName, currentMovies);
        }
    }

    public Movie findMovieByName(String name) {
        return movies.get(name);
    }

    public Director findDirectorByName(String name) {
        return directors.get(name);
    }

    public List<String> findMoviesByDirectorName(String director) {
        return directorMovieMap.getOrDefault(director, new ArrayList<>());
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(movies.keySet());
    }

    public void deleteDirector(String directorName) {
        List<String>movieslist = new ArrayList<>();
        if(directorMovieMap.containsKey(directorName)) {
            movieslist = directorMovieMap.get(directorName);

            for (String movie : movieslist) {
                if (movies.containsKey(movie)) {
                    movies.remove(movie);
                }
            }

            directorMovieMap.remove(directorName);
        }

        if(directors.containsKey(directorName)){
            directors.remove(directorName);
        }
    }

    public void deleteAllDirectors() {

        HashSet<String> movieSet = new HashSet<>();

        for(String director : directorMovieMap.keySet()){
            List<String> movielist = directorMovieMap.get(director);
            for (String movie : movielist){
                movielist.add(movie);
            }
        }

        for (String movie : movieSet){
            if(movies.containsKey(movie)){
                movies.remove(movie);
            }
        }

    }
}
