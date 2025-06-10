package com.example.bookmyshowapplication.services;

import com.example.bookmyshowapplication.exceptions.ShowSeatsNotAvailableException;
import com.example.bookmyshowapplication.exceptions.UserNotFoundException;
import com.example.bookmyshowapplication.models.Booking;
import com.example.bookmyshowapplication.models.ShowSeat;
import com.example.bookmyshowapplication.models.User;
import com.example.bookmyshowapplication.models.enums.BookingStatus;
import com.example.bookmyshowapplication.models.enums.ShowSeatStatus;
import com.example.bookmyshowapplication.repositories.BookingRepository;
import com.example.bookmyshowapplication.repositories.ShowSeatRepository;
import com.example.bookmyshowapplication.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowSeatRepository showSeatRepository;
    private BookingPriceCalculateService bookingPriceCalculateService;
    private BookingRepository bookingRepository;

    public BookingService(UserRepository userRepository, ShowSeatRepository showSeatRepository, BookingPriceCalculateService bookingPriceCalculateService, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingPriceCalculateService = bookingPriceCalculateService;
        this.bookingRepository = bookingRepository;
    }
    //we won't provide request dto here and provide parameters. Request dto and all should provide in booking controller not in booking service
    //Also this is service layer

    //In dbms, we discussed isolation level = Read committed, Read uncommited, Repeatable Read, Serializable)
    //HERE, SERIALIZABLE provides the best concurrency results
    @Transactional(isolation = Isolation.SERIALIZABLE)
        public Booking createBooking(Long userId, List<Long> showSeatIdsList) throws UserNotFoundException, ShowSeatsNotAvailableException {

        //Create the booking object, then set the user, showSeatIdsList...
        Booking booking = new Booking();
        /*
            Approach 1:
            1. Fetch the user from userId
            2. If the user is not found, then we throw an exception
            3. Fetch the ShowSeat objects from the database of the particular user
            4. Check if the showSeats are available
            5. If showSeats are available, then BLOCKED the seats.
            6. After blocking the seats, then calculate the entire amount for the booking show
            7. Create the booking for that particular user and return the booking back to the client
            8. Approach 1: Now take  a lock for entire booking, then, unlocking later. So, one user will create booking at one time (i.e) take a lock on entirely
               This will run sequentially not in parallel

            Approach 2: Run it in parallel like double check locking. See the steps in comment section

            //Let's do it in Approach 1

         */

        //First, fetch the user from userId, so we need UserRepository
        Optional<User> optionalUser = userRepository.findById(userId);

        //You want the caller to recover from this failure or take from action
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with userId" + userId + " is not valid");
        }
        //if the user object is present in the UserRepository, then get the user object
        User user = optionalUser.get();

        /********************************************************************************************************************************************************/

        //Second, get the show seat object
        List<ShowSeat> showSeatList = showSeatRepository.findAllById(showSeatIdsList);

        //if there is no show seat in list of showSeats, then we can tell to choose the seat in the show
        if(showSeatList.size()==0){
            throw new RuntimeException("Please select at least one Show Seat");
        }
        //if the seats are not available in the show, then throw the exception with message
        //iterate the seat first
        for(ShowSeat showSeat : showSeatList){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                //cannot book this show
                throw new ShowSeatsNotAvailableException("ShowSeat with showId: " + showSeat.getShow().getId() + " and seatId:  " + showSeat.getSeat().getSeatNumber() + " is not available");
            }
        }

        //if the seats available in the show, then you are trying to book, so you blocked the seat. Once payment completed, then you booked the seat.
        //Here, you have to save the sowSeat status in showSeatRepository to the database
        for(ShowSeat showSeat : showSeatList){
            //blocking the seat status in in-memory not in database
            //now, save to the database, some of the JPA methods is build...so, no need to type save method in showSeatRepository
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeatRepository.save(showSeat);
        }

        /********************************************************************************************************************************************************/

        //now make the booking process...
        //Create the booking object and move to the payment object
        booking.setUser(user);
        //set all the showSeatList what we have done already
        booking.setShowSeatList(showSeatList);
        //set the status is pending because one he/she paid the amount then it would become BookingStatus.PENDING
        booking.setBookingStatus(BookingStatus.PENDING);

        //based on the show and seat type(ShowSeatType class), we calculate the price or amount
        //For 1 show, I have to book 10 seats. For that 1 particular show, there can be multiple seat type, so price is unique for each seat type
        //So, calculate the price first. To maintain single responsibility principle, calculate the price in different service. So, let's create another class called
        //with @Service annotation
        booking.setBookingAmount(bookingPriceCalculateService.calculatePrice(showSeatList));

        //After booking, move to the payment page

        /*
        Here, 3rd party payment gateway will work that is integrating with Razorpay integration
        Razorpay confirms the payment and sends you the reference id
        once get the reference id and payment is successful, then booking is confirmed
        now change the BookingStatus to confirmed -> BookingStatus.CONFIRMED
        if the payment fails, then BookingStatus is still pending -> BookingStatus.PENDING
        */

        /*
        Update booking status and seat status in database:

        Save the booking and showSeatStatus in database, then only we get booking object and
        return the booking object to the client.
        if booking succeeds -> make the showSeatStatus as permanently BOOKED
        if booking fails -> make the showSeatStatus AVAILABLE
        */

        /*
        if anything fails either of the above steps, the controller will handle that.
        Controller see the catch block and see the responseStatus as FAILED,
        and controller tells the client the status as FAILED, it means request is FAILED
         */

        //return the booking back to the client. Save the booking in databasee then only you get id here. So, need
        return bookingRepository.save(booking);
    }
}
//Approach 1: We do it in approach 1
//Approach 2: Double check locking
/*
1) Get the user object with the user id
2) Get the show object with the show id
3) Get all the show seat objects from the showSeatIdList
4) Now, check if all the seats are available…
        a. If no, then throw an exception
        b. If yes, proceed with the booking
             i.	Put a lock
            ii.	Mark the selected seats as BLOCKED
           iii.	Release a lock
            iv.	Create the booking and make the payment
             v.	If payment succeeds
                   1. Again, put a lock here
                   2. Mark the selected seats as BOOKED
                   3. Release a lock
                   4. Return the booking ticket to the user
             vi. If payment fails (or) timer expires
                   1. Again, put a lock here
                   2. Mark the selected seats as AVAILABLE
                   3. Release a lock

*/

