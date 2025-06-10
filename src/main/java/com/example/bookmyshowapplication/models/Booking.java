package com.example.bookmyshowapplication.models;

import com.example.bookmyshowapplication.models.enums.BookingStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    //Every booking have user
    @ManyToOne
    private User user;
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
    private double BookingAmount;
    @ManyToMany
    private List<ShowSeat> showSeatList;
    @OneToMany
    @JoinColumn(name = "booking_id")
    private List<Payment> paymentList;
    private Date movieBookedAt;

}

/*
Booking : User
1  :  1
m  :  1
--------
m  :  1

Booking : ShowSeat
     1  :  m  1 booking have multiple show seat obviously
     m  :  1  if anybody cancelled the booking then same showSeat be assigned to some other booking. So, 1 ShowSeat have multiple booking
     -------
     m  :  m

Booking : Payment
1 : m
1 : 1
-----
1 : m

 */