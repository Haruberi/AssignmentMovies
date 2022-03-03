package com.movies.assignmentmovies.model;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.tomcat.jni.Library;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    private String title;
    private String genre;
    private Integer releaseYear;
    private String director;
    private String picture;
    private String trailer;

    //ManyToMany : Movie and Character
    @ManyToMany
    @JoinTable(
            name="character_movie",
            joinColumns = {@JoinColumn(name = "characters_character_id" )},
            inverseJoinColumns = {
                    @JoinColumn(name="movies_movie_id")
            }
    )
    public Set<Character> characters = new HashSet<>();

    @JsonGetter("characters")
    public List<String> movies() {
        if (characters != null) {
            return characters.stream()
                    .map(character -> {
                        return "/api/v1/character/" + character.getCharacterId();
                    }).collect(Collectors.toList());
        }
        return null;
    }

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;
    @JsonGetter("franchise")
    public String franchise() {
        if(franchise != null){
            return "/api/v1/franchises/" + franchise.getFranchiseId();
        }else{
            return null;
        }
    }

    //Set<Franchise> franchises;

    public Movie(Long movieId, String title, String genre, Integer releaseYear, String director, String picture, String trailer) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.director = director;
        this.picture = picture;
        this.trailer = trailer;
    }

    public Movie() {
        super();
    }

    public Long getId() {
        return movieId;
    }

    public void setId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String url) {
        this.picture = url;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
}
