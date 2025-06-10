package com.example.bookmyshowapplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatType extends BaseModel{
    @ManyToOne
    private Show show;
    @ManyToOne
    private SeatType seatType;
    private double price;
}
/*
//Mostly combination of 2 objects (i.e) ShowSeat have @ManyToOne cardinality

ShowSeatType: Show
1 : 1
m : 1
------------------
m : 1
------------------

ShowSeatType : Seat
1 : 1
m : 1
-------------------
m : 1
-------------------
*/