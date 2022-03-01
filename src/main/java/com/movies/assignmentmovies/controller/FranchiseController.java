package com.movies.assignmentmovies.controller;

import com.movies.assignmentmovies.model.Franchise;
import com.movies.assignmentmovies.repository.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/franchises")
public class FranchiseController {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @GetMapping()
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        List<Franchise> franchises = franchiseRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(franchises, status);
    }
/*
    @PostMapping("/{id}")
    public ResponseEntity<Franchise> addFranchise(@RequestBody Franchise franchise) {
        HttpStatus status;
        franchise = franchiseRepository.save(franchise);
        status = HttpStatus.CREATED;
        return new ResponseEntity<>(franchise, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Franchise> updateFranchise(@PathVariable Long id, @RequestBody Franchise franchise) {
        Franchise returnFranchise = new Franchise();
        HttpStatus status;

        if (!id.equals(franchise.getFranchiseId())) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnFranchise, status);
        }*/

}
