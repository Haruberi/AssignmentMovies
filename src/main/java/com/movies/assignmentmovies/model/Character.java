package com.movies.assignmentmovies.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long characterId;
    @Column(name= "full_name")
    private String fullName;
    @Column(name= "alias")
    private String alias;
    @Column(name= "gender")
    private String gender;
    @Column(name= "url")
    private String url;

    @OneToMany
    @JoinColumn(name = "character_id")
    Set<Movie> movies;

    @JsonGetter("movies")
    public List<String> characters(){
        if(movies != null){
            return movies.stream()
                    .map(movie -> {
                        return "/api/v1/movies/" + movie.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }


    public Character() { }

    public Character(String fullName, String alias, String gender, String url) {
        this.fullName = fullName;
        this.alias = alias;
        this.gender = gender;
        this.url = url;
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
