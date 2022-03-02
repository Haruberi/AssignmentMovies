package com.movies.assignmentmovies.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Character {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long characterId;
    private String fullName;
    private String alias;
    private String gender;
    private String url;


    //ManyToMany : Character and Movie
    @ManyToMany(mappedBy = "characters", fetch = FetchType.LAZY)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Movie> movies;

    @JsonGetter("movies")
    public List<String> characters() {
        if (movies != null) {
            return movies.stream()
                    .map(movie -> {
                        return "/api/v1/movie/" + movie.getId();
                        //return movie.getTitle();
                    }).collect(Collectors.toList());
        }
        return null;
    }

    public Character() { }

    public Character(String fullName, String alias, String gender, String url, List<Movie> movies) {
        this.fullName = fullName;
        this.alias = alias;
        this.gender = gender;
        this.url = url;
        this.movies = movies;
    }

    public Long getCharacterId() {
        return characterId;
    }
    public void setCharacterId(Long id) {
        this.characterId = id;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
