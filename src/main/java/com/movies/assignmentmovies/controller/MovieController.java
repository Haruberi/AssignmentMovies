package com.movies.assignmentmovies.controller;

import com.movies.assignmentmovies.model.Character;
import com.movies.assignmentmovies.model.Movie;
import com.movies.assignmentmovies.repository.CharacterRepository;
import com.movies.assignmentmovies.repository.MovieRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Tag(name="Movie")
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CharacterRepository characterRepository;
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

    @GetMapping(value = "/characters/movie")
    public ResponseEntity<List<Character>> getCharactersInMovie(){
        List<Character> characters = characterRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(characters, status);
    }

    /**
     * Create a new movie.
     * @param movie
     * @return
     */
    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        HttpStatus status;
        movie = movieRepository.save(movie);
        status = HttpStatus.CREATED;
        return new ResponseEntity<>(movie, status);
    }

    /**
     * Update an existing movie.
     * @param id
     * @param movie
     * @return
     */
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

    @PatchMapping("/{movie_id}/characters")
        public Movie updateCharactersInMovie(@RequestBody Long[] characterIds, @PathVariable Long movie_id) {
            if (!movieRepository.existsById(movie_id)) { return null; }

            List<Character> characters = new ArrayList<>();
            for (Long characterId: characterIds) {
                Character character = characterRepository.getById(characterId);
                characters.add(character);
            }
            Movie movie = movieRepository.getById(movie_id);
            movie.setCharacters(characters);
            return movieRepository.save(movie);
        }


    /**
     * Delete a movie.
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMovie(@PathVariable("id") long id) {
        movieRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Delete all movies.
     * @return
     */
    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAllMovies() {
        movieRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
