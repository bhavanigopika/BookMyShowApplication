package com.example.bookmyshowapplication.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private String seatNumber;
    private int rowVal;
    private int colVal;
    @ManyToOne
    private SeatType seatType;

}
/*
Seat : SeatType
1 : 1
m : 1
----------------
m : 1
----------------
 */