package com.movies.assignmentmovies.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long franchiseId;
    @Column(name="name")
        private String name;
    @Column(name="description")
        private String description;

    @JsonGetter("movies")
    public List<String> getMoviesList() {
        return movies.stream()
                .map(movie -> {
                    return "/movie" + movie.getId();
                }).collect(Collectors.toList());
    }

    @OneToMany(mappedBy = "movieFranchise", fetch = FetchType.LAZY)
    List<Movie> movies = new ArrayList<>();
    /*@JsonGetter("movies")
    public List<String> movies(){
        if(movies != null){
            return movies.stream()
                    .map(book -> {
                        //return "/movie" + movies
                        return book.getTitle();
                    }).collect(Collectors.toList());
        }
        return null;

    }

    //OnetoMany : Movie and Franchise
    @OneToMany(mappedBy = "franchise")
    Set<Movie> movies;*/


    public Franchise() { }

    public Franchise(Long franchiseId, String name, String description) {
        this.franchiseId = franchiseId;
        this.name = name;
        this.description = description;
    }

    public Long getFranchiseId() {
        return franchiseId;
    }
    public void setFranchiseId(Long id) {
        this.franchiseId = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Franchise{" +
                "id=" + franchiseId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
