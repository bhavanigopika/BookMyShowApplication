package com.example.bookmyshowapplication.controllers;

import com.example.bookmyshowapplication.dtos.CreateBookingRequestDto;
import com.example.bookmyshowapplication.dtos.CreateBookingResponseDto;
import com.example.bookmyshowapplication.dtos.ResponseStatus;
import com.example.bookmyshowapplication.models.Booking;
import com.example.bookmyshowapplication.models.ShowSeat;
import com.example.bookmyshowapplication.models.User;
import com.example.bookmyshowapplication.services.BookingService;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private BookingService bookingService;

    //Apply constructor dependency injection
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    //to create booking, we need user, user going to book which show and seat, so we take show seat(1 booking have multiple show seat) -> here user going to provide userId and showSeatId
    //Would you take input as parameter? No, we should take as dto(request dto then we get the response dto)
    //provide post mapping because you provided @RequestBody
    @PostMapping("/create")
    public CreateBookingResponseDto createBooking(@RequestBody CreateBookingRequestDto createBookingRequestDto){

        //set the booking and response status in createBookingResponseDto
        CreateBookingResponseDto createBookingResponseDto = new CreateBookingResponseDto();
        try {

            Booking booking = bookingService.createBooking(createBookingRequestDto.getUserId(), createBookingRequestDto.getShowSeatIdsList());

            createBookingResponseDto.setBooking(booking);
            createBookingResponseDto.setResponseStatus(ResponseStatus.SUCCESS);

            return createBookingResponseDto;
        } catch (Exception e) {

            createBookingResponseDto.setResponseStatus(ResponseStatus.FAILURE);

        }
        return createBookingResponseDto;

        //or

        /*

        Booking booking = null;
        booking = bookingService.createBooking(createBookingRequestDto.getUserId(), createBookingRequestDto.getShowSeatIdsList());
        createBookingResponseDto.setBooking(booking);
        createBookingResponseDto.setResponseStatus(ResponseStatus.SUCCESS);

        if(booking == null){
            createBookingResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return createBookingResponseDto;

        */
    }
}
