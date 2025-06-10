package com.example.bookmyshowapplication.models;

import com.example.bookmyshowapplication.models.enums.Feature;
import com.example.bookmyshowapplication.models.enums.ScreenStatus;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel{
    private String screenName;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection//Why this @ElemenCollections, because it has list of enums
    private List<Feature> featureList;
    @OneToMany
    @JoinColumn(name = "screen_id)")
    private List<Seat> seatList;
    private ScreenStatus screenStatus;
    /*
    @ManyToOne
    private Theatre theatre;
    */
}

/*
Theatre : Screen
1   :   m
1   :   1
------------------
1   :   m   -> we have to add theatre_id in screen class
            -> in class for these models, we add list of screen in theatre class
------------------
So,
Screen : Theatre
m : 1

Screen : Seat
1 : m
1 : 1
--------------
1 : m


Screen : Show
1 : m
1: 1
-------
1 : m
-------
*/