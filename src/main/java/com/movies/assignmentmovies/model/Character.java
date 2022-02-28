package com.movies.assignmentmovies.model;

import javax.persistence.*;

@Entity
@Table(name = "character")
public class Character {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "full_name")
    private String fullName;
    @Column(name = "alias")
    private String alias;
    @Column(name = "gender")
    private String gender;
    @Column(name = "url")
    private String url;

    public Character() { }

    public Character(String fullName, String alias, String gender, String url) {
        this.fullName = fullName;
        this.alias = alias;
        this.gender = gender;
        this.url = url;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
