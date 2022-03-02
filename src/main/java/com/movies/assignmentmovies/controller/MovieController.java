package com.movies.assignmentmovies.controller;

import com.movies.assignmentmovies.model.Character;
import com.movies.assignmentmovies.model.Movie;
import com.movies.assignmentmovies.repository.MovieRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name="Movie")
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;
    /**
     * Execute to get all the movies.
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(movies, status);
    }
    /**
     * Enter an ID to get the desired movie.
     * @param id
     * @return
     */
    @GetMapping(value="/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie returnMovie = new Movie();
        HttpStatus status;

        if (movieRepository.existsById(id)) {
            status = HttpStatus.OK;
            returnMovie = movieRepository.findById(id).get();
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnMovie,status);
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        HttpStatus status;
        movie = movieRepository.save(movie);
        status = HttpStatus.CREATED;
        return new ResponseEntity<>(movie, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie){
        Movie returnMovie = new Movie();
        HttpStatus status;

        if(!id.equals(movie.getId())){
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnMovie, status);
        }
        returnMovie = movieRepository.save(movie);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(returnMovie, status);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCharacter(@PathVariable("id") long id) {
        movieRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Delete all characters.
     * @return
     */
    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAllCharacters() {
        movieRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
