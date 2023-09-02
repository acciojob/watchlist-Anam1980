package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return new ResponseEntity("Movie added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director) {
        movieService.addDirector(director);
        return new ResponseEntity("Director added successfully",HttpStatus.CREATED);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movieName") String movieName, @RequestParam("directorName") String directorName) {
        movieService.addMovieDirectorPair(movieName, directorName);
        return new ResponseEntity("Movie-Director pair added successfully", HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String name) {
        Movie movie = movieService.getMovieByName(name);

//        if(movie==null){
//            return  new ResponseEntity("Movie doesn't exist", HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity(movie, HttpStatus.FOUND);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String name) {
        Director director = movieService.getDirectorByName(name);
//        if(director==null){
//            return new ResponseEntity("Director doesn't exist", HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity(director, HttpStatus.FOUND);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String director) {
        List<String> movies = movieService.getMoviesByDirectorName(director);
//        if(movies==null){
//            return new ResponseEntity("MoviesList doesn't exist", HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity(movies, HttpStatus.FOUND);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies() {
        List<String> movies = movieService.findAllMovies();
//        if(movies==null){
//            return new ResponseEntity("AllMoviesList doesn't exist", HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity(movies, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("director'sName") String directorName) {
        movieService.deleteDirectorByName(directorName);

        return new ResponseEntity("Director and their movies deleted successfully", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors() {
        movieService.deleteAllDirectors();
        return new ResponseEntity("All directors and their movies deleted successfully", HttpStatus.ACCEPTED);
    }
}
