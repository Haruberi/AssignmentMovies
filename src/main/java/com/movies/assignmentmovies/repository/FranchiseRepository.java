package com.movies.assignmentmovies.repository;

import com.movies.assignmentmovies.model.Franchise;
import com.movies.assignmentmovies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
}
