package com.movies.assignmentmovies.model;

import javax.persistence.*;

@Entity
public class Franchise {

    @Id
    @GeneratedValue
        private Long franchiseId;
    @Column(name="name")
        private String name;
    @Column(name="description")
        private String description;

    //OnetoOne : Movie and Franchise

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
