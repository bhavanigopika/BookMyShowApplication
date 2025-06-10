package com.example.bookmyshowapplication.repositories;

import com.example.bookmyshowapplication.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    //Find all show seat id
    /*
    select * from show_seats where id IN (1,4,5,7,8)
     */
    //Return all the list of show seat
    @Override
    List<ShowSeat> findAllById(Iterable<Long> showSeatIdsList);

    @Override
    <S extends ShowSeat> S save(S entity);
}
