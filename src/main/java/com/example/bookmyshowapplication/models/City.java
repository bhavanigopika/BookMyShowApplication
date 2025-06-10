package com.example.bookmyshowapplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class City extends BaseModel{
    private String cityName;
    @OneToMany
    @JoinColumn(name = "city_id")
    private List<Theatre> theatreList;

}
/*
City : Theatre
1  :  m
1  :  1
--------
1  : m
 */