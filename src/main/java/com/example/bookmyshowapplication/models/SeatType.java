package com.example.bookmyshowapplication.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

//value is fixed(not change it for every time), so we go for seatType be enum instead of boolean
//in enum, it internally coded as 0,1,2... not as word...
//but why can we use class? Suppose, If I want to add one more seat type as CLASSIC, then I have to add it in seat type class,
//then I will deploy the code. It could take some time. Also, If I add CLASSIC after SILVER, then PLATINUM mapping code become 3 instead of 2, then everything become collapsed
//Enum also store internally in table
//Better go with seatType as class
/*
public enum SeatType {
    GOLD,//0
    SILVER,//1
    PLATINUM//2
}
*/
//Class also will add one more row in table with new type of value
@Getter
@Setter
@Entity
public class SeatType extends BaseModel{
    private String seatTypeName;
}
