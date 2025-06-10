package com.example.bookmyshowapplication.repositories;

import com.example.bookmyshowapplication.models.Show;
import com.example.bookmyshowapplication.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
    /*
    I need the query as -> for a particular show, I need the type

    -> select * from show_seat_type where show_id = 123

    ---- ShowSeatType table ----

    show seatType price
    123  GOLD      500
    123  SILVER    300
    123  PLATINUM  700

    So, get all the show by ids
    */
    //We will get all the show in list
    //Write our own JPA methods(custom)
    List<ShowSeatType> findAllByShow(Show show);
    List<ShowSeatType> findAllByShowId(Long showId);
}
