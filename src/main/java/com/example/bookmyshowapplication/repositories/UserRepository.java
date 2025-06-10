package com.example.bookmyshowapplication.repositories;

import com.example.bookmyshowapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long userId);

    //Write our own JPA methods(custom)
    Optional<User> findByEmail(String email);

    @Override
    <S extends User> S save(S entity);
}
