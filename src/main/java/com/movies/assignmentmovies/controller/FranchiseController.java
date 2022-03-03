package com.movies.assignmentmovies.controller;

import com.movies.assignmentmovies.model.Character;
import com.movies.assignmentmovies.model.Franchise;
import com.movies.assignmentmovies.repository.CharacterRepository;
import com.movies.assignmentmovies.repository.FranchiseRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name="Franchise")
@RequestMapping(value="/api/v1/franchises")
public class FranchiseController {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private CharacterRepository characterRepository;

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
