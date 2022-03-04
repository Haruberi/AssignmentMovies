package com.movies.assignmentmovies.controller;

import com.movies.assignmentmovies.model.Character;
import com.movies.assignmentmovies.model.Franchise;
import com.movies.assignmentmovies.model.Movie;
import com.movies.assignmentmovies.repository.FranchiseRepository;
import com.movies.assignmentmovies.repository.MovieRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.type.CharacterArrayClobType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name="Franchise")
@RequestMapping(value="/api/v1/franchises")
public class FranchiseController {

    @Autowired
    private FranchiseRepository franchiseRepository;
    @Autowired
    private MovieRepository movieRepository;

    /**
     * Execute to get all the franchises
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        List<Franchise> franchises = franchiseRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(franchises, status);
    }

    /**
     * Get all characters in Franchise
     * @param franchise_id
     * @return
     */
    @GetMapping("/characters/{franchise_id}")
    public ResponseEntity<List<Character>>getAllCharactersInFranchise(@PathVariable long franchise_id) {
      List<Character> characters = franchiseRepository.getAllCharactersInFranchise(franchise_id);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(characters, status);
    }

    /**
     * Enter an ID to get the desired franchise.
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Franchise> getFranchiseById(@PathVariable Long id) {
        Franchise returnFranchise = new Franchise();
        HttpStatus status;

        if (franchiseRepository.existsById(id)) {
            status = HttpStatus.OK;
            returnFranchise = franchiseRepository.findById(id).get();
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnFranchise, status);
    }

    /**
     * Get all movies in franchise.
     * @return
     */
    @GetMapping("/movies/")
    public ResponseEntity<List<Movie>> getAllMoviesInFranchise() {
        List<Movie> getAllMovies = movieRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(getAllMovies, status);
    }

    /**
     * Create a new franchise.
     * @param franchise
     * @return
     */
    @PostMapping("/franchise")
    public ResponseEntity<Franchise> addFranchise(@RequestBody Franchise franchise) {
        HttpStatus status;
        franchise = franchiseRepository.save(franchise);
        status = HttpStatus.CREATED;
        return new ResponseEntity<>(franchise, status);
    }

    /**
     * Update an existing franchise.
     * @param id
     * @param franchise
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Franchise> updateFranchise(@PathVariable Long id, @RequestBody Franchise franchise) {
        Franchise returnFranchise = new Franchise();
        HttpStatus status;

        if (!id.equals(franchise.getFranchiseId())) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnFranchise, status);
        }
        returnFranchise = franchiseRepository.save(franchise);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(returnFranchise, status);
    }

    /**
     * Update movies in a franchise
     * @param movieIds
     * @param movie_id
     * @return
     */
    @PatchMapping("/{movie_id}/movies")
    public Franchise updateMoviesInFranchise(@RequestBody long[] movieIds, @PathVariable long movie_id) {
        if (!franchiseRepository.existsById(movie_id)) { return null; }

        List<Movie> movies = new ArrayList<>();
        for (long movieId: movieIds) {
            Movie movie = movieRepository.getById(movieId);
            movies.add(movie);
        }
        Franchise franchise = franchiseRepository.getById(movie_id);
        franchise.setFranchiseId(movie_id);
        return franchiseRepository.save(franchise);
    }

    /**
     * Delete Franchise by Id
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFranchise(@PathVariable("id") long id) {
        franchiseRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Deelete all franchises.
     * @return
     */
    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAllFranchises() {
        franchiseRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
