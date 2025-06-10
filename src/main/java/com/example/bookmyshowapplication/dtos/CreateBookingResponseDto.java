package com.example.bookmyshowapplication.dtos;

import com.example.bookmyshowapplication.models.Booking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//we should give back the booking/ticket to the user and response status
public class CreateBookingResponseDto {
    private Booking booking;
    private ResponseStatus responseStatus;
}
