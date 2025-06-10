package com.example.bookmyshowapplication.models;

import com.example.bookmyshowapplication.models.enums.Feature;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Movie extends BaseModel{
    private String movieName;
    private double rating;
    private int durationInMinutes;
    //1 movie have list of languages
    //so take language in enum because language is limited
    //Here Feature is enum so make it as ordinal where it stores with integer id = 0, 1...
    //Also, it is list of feature so, provide ElementCollection(it means not storing 1 enum, it is storing list of feature(feature is enum))
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection//Why this @ElemenCollections, because it has list of enums
    private List<Feature> featureList;
}
/*
Here, we won't do cardinality between movie and feature because feature is enum not a class
Other movieName, rating, durationInMinutes are primitive attributes
 */