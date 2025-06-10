package com.example.bookmyshowapplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel {
    private String theatreName;
    private String address;
    @ManyToOne
    private City city;
    @OneToMany
    @JoinColumn(name="theatre_id")
    private List<Screen> screenList;
}
/*
Theatre : Screen
 1 : m
 1 : 1
 ------
 1 : m
 ------

 Theatre : City
 1 : 1
 m : 1
 ---------------
 m : 1
 ---------------
 */