package com.lufegaba.bnb.domain.repositories;

import com.lufegaba.bnb.domain.Lodging;
import com.lufegaba.bnb.domain.Reservation;
import com.lufegaba.bnb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByGuest (User guest);
    List<Reservation> findByLodgingReserved (Lodging lodgingReserved);
}
