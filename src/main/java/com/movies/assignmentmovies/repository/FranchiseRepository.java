package com.movies.assignmentmovies.repository;

import com.movies.assignmentmovies.model.Character;
import com.movies.assignmentmovies.model.Franchise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Long> {

    @Query(value = "SELECT cha FROM character_franchise AS c JOIN character AS cha ON cha.character_id = c.character_id WHERE c.franchise_id = ?1", nativeQuery = true)
    //@Query(value="SELECT * FROM character_franchise AS cf JOIN character AS f ON cf.franchise_id=f.franchise_id")
    //@Query(value="SELECT * FROM character_franchise AS cf JOIN TABLE_NAME = <character> AS c ON ch.franchise_id = c.character_id = ?1", nativeQuery = true)
    //@Query(value="SELECT * FROM character_franchise JOIN sys.tables character ON ")
    //@Query(value = "SELECT * FROM character_franchise AS cf JOIN franchise AS f ON f.franchise_id = cf.franchise_id WHERE cf.character_id = ?1", nativeQuery = true)
    List<Character> getAllCharactersInFranchise(long character_id);
}
