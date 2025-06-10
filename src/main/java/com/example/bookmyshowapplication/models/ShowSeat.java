package com.example.bookmyshowapplication.models;

import com.example.bookmyshowapplication.models.enums.ShowSeatStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel{
    @ManyToOne
    private Show show;
    @ManyToOne
    private Seat seat;
    @Enumerated(EnumType.ORDINAL)
    private ShowSeatStatus showSeatStatus;

}
/*
show with id 123 have seat 1 => 123 -> 1 => showseat 1 -> this particular object have id = 1
show with id 123 have seat 2 => 123 -> 2 => showseat 2 -> this particular object have id = 2
show with id 123 have seat 3 => 123 -> 3 => showseat 3 -> this particular object have id = 3

 */
/*
//Mostly combination of 2 objects (i.e) ShowSeat have @ManyToOne cardinality
ShowSeat : Show
 1 : 1
 m : 1
 --------------
 m : 1
 --------------
ShowSeat                 :     Show
S1A1 -> Show 1 Seat A1   :   S1 -> show 1
S1A2 -> Show 1 Seat A2   :   S1 -> show 1
S2A1 -> Show 2 Seat A1   :   S2 -> show 1
S2A2 -> Show 2 Seat A2   :   S2 -> show 2
S3B1 -> Show 3 Seat B1   :   S3 -> show 3
For show 1, we have A1, A2 seat -> 1 show many show seat
For 1 show seat say S1A1 we have only one show (i.e) for, S1A1 we have show 1 only, for S2A2 we show 2 only
For 1 show say S1 we have S1A1, S1A2

___________________________________________________________________________________________________

ShowSeat:Seat
1 : 1
m : 1
-------------
m : 1
-------------

ShowSeat                 :     Seat
S1A1 -> Show 1 Seat A1   :   A1 -> seat A1
S1A2 -> Show 1 Seat A2   :   A2 -> seat A2
S2A1 -> Show 2 Seat A1   :   A1 -> seat A1
S2A2 -> Show 2 Seat A2   :   A2 -> seat A2
S3B1 -> Show 3 Seat B1   :   B1 -> seat B1
For show 1, we have A1, A2 seat -> 1 seat many show seat
For 1 show seat we have only one seat (i.e) for, S1A1 we have seat A1 only, for S2A2 we seat A2 only
For 1 seat say A1 we have S1A1, S2A1
*/