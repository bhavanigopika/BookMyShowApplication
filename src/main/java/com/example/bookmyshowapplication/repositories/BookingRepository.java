package com.example.bookmyshowapplication.repositories;

import com.example.bookmyshowapplication.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Override
    <S extends Booking> S save(S entity);
}
