package com.example.bookmyshowapplication.dtos;

import com.example.bookmyshowapplication.models.ShowSeat;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateBookingRequestDto {
    private Long userId;
    private List<Long> showSeatIdsList;
}