package com.example.bookmyshowapplication.models;

import com.example.bookmyshowapplication.models.enums.Feature;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
//show is the reserved keyword in mysql and table not created in the name of show
//so, change the name of show into shows...now table shows is created
@Entity(name = "shows")
public class Show extends BaseModel{
    @ManyToOne
    private Movie movie;
    private Date startTime;
    private Date endTime;
    @ManyToOne
    private Screen screen;
    @Enumerated(EnumType.STRING)
    @ElementCollection//Why this element collections, list of feature should store in lists
    private List<Feature> featureList;

}
/*
Show  :  Screen
1 : 1
m : 1
-------
m : 1
-------

Show  :  Movie
1 : 1
m : 1
-------
m : 1
-------

Show -> Feature , here feature is enum, cannot do cardinality here
*/