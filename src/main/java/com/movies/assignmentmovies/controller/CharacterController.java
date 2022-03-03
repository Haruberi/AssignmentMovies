package com.movies.assignmentmovies.controller;

import com.movies.assignmentmovies.model.Character;
import com.movies.assignmentmovies.model.Movie;
import com.movies.assignmentmovies.repository.CharacterRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name="Character")
@RequestMapping(value="api/v1/characters")
public class CharacterController {

    @Autowired
    private CharacterRepository characterRepository;

    /**
     * Execute to get all the characters.
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<List<Character>> getAllCharacters() {
        List<Character> characters = characterRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(characters, status);
    }

    /**
     * Enter an ID to get the desired character.
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable Long id){
        Character returnChar = new Character();
        HttpStatus status;

        if(characterRepository.existsById(id)){
            status = HttpStatus.OK;
            returnChar = characterRepository.findById(id).get();
        }else{
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnChar, status);
    }

    /**
     * Create a new character
     * @param character
     * @return
     */
    @PostMapping
    public ResponseEntity<Character> addCharacter(@RequestBody Character character) {
        HttpStatus status;
        character = characterRepository.save(character);
        status = HttpStatus.CREATED;
        return new ResponseEntity<>(character, status);
    }

    /**
     * Enter an ID to update a desired character.
     * @param id
     * @param character
     * @return
     */
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


    /**
     * Enter an ID to delete a desired character.
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCharacter(@PathVariable("id") long id) {
        characterRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Delete all characters.
     * @return
     */
    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAllCharacters() {
        characterRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
