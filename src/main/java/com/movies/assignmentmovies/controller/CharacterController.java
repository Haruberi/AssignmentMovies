package com.movies.assignmentmovies.controller;

import com.movies.assignmentmovies.model.Character;
import com.movies.assignmentmovies.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1/characters")
public class CharacterController {

    @Autowired
    private CharacterRepository characterRepository;

    @GetMapping()
    public ResponseEntity<List<Character>> getAllCharacters() {
        List<Character> characters = characterRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(characters,status);
    }

    @PostMapping
    public ResponseEntity<Character> addCharacter(@RequestBody Character character) {
        HttpStatus status;
        character = characterRepository.save(character);
        status = HttpStatus.CREATED;
        return new ResponseEntity<>(character, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable Long id, @RequestBody Character character) {
        Character returnCharacter = new Character();
        HttpStatus status;

        if (!id.equals(character.getCharacterId())) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnCharacter, status);
        }
        returnCharacter = characterRepository.save(character);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(returnCharacter, status);
    }
}
