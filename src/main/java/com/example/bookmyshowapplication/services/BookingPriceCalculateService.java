package com.example.bookmyshowapplication.services;

import com.example.bookmyshowapplication.models.Show;
import com.example.bookmyshowapplication.models.ShowSeat;
import com.example.bookmyshowapplication.models.ShowSeatType;
import com.example.bookmyshowapplication.repositories.ShowSeatRepository;
import com.example.bookmyshowapplication.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingPriceCalculateService {
    private ShowSeatTypeRepository showSeatTypeRepository;
    public BookingPriceCalculateService(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }
    //calculate the price based on showSeat. I am having list of showSeat, so pass this parameter now
    public double calculatePrice(List<ShowSeat> showSeatList) {
        double amount = 0.0;

        //We need to get the type of show seat? YES, so need ShowSeatType class from models and showSeatType table from repositories
        //Get the show object from one of the showSeatList,
        //Here, show object will be same for all the show seats because at once you are allowing to book for only one show
        //Show show = showSeatList.getFirst().getShow();
        Show show = showSeatList.get(0).getShow();

        //List<ShowSeatType> showSeatTypeList = showSeatTypeRepository.findAllByShow(show);
        List<ShowSeatType> showSeatTypeList = showSeatTypeRepository.findAllByShowId(show.getId());

        //For the show 123, give all showSeatTypeList, so we get show = 123, related seatType for this 123 show such as GOLD, SILVER, PLATINUM and
        //related price for this 123 show such as 500, 300, 700 to their corresponding seat type
        /*
        select * from show_seat_type where show_id = 123

            ---- ShowSeatType table ----

            show seatType price
            123  GOLD      500 - object 1
            123  SILVER    300 - object 2
            123  PLATINUM  700 - object 3 -> These are the 3 objects in the list

            ---- ShowSeat ----

            show seat status
            123   A1  AVAILABLE
            123   A2  BOOKED
            123   B3  BLOCKED
        */

        //iterate every show seat
        for(ShowSeat showSeat : showSeatList){//{123, A1, BOOKED}, {123, A2, AVAILABLE}, {123, B3, BLOCKED}
            //for every show seat type
            for(ShowSeatType showSeatType : showSeatTypeList){//Say, you pick A1, then try to match it with object 1, object 2, object 3 in showSeatType(see above)
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())){//if A1 seat IN ShowSeat table is PLATINUM seatType then match it with ShowSeatType table "123, PLATINUM, 700". Once match, then add the amount (i.e) 700 with the actual amount
                    amount = amount + showSeatType.getPrice();
                    //I got the amount for the seatType for that show. If I got PLATINUM seat type, then I break it because one seat associated with one seatType logically (i.e) A1 seat would be PLATINUM seat type. It would not be GOLD or SILVER at a time
                    break;
                }
            }
        }
        return amount;


    }
}
