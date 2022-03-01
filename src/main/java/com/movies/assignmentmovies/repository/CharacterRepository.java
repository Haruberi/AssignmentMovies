package com.movies.assignmentmovies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.movies.assignmentmovies.model.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

}
