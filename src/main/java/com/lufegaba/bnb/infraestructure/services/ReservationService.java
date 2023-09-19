package com.lufegaba.bnb.infraestructure.services;

import com.lufegaba.bnb.domain.Reservation;
import com.lufegaba.bnb.domain.repositories.LodgingRepository;
import com.lufegaba.bnb.domain.repositories.ReservationRepository;
import com.lufegaba.bnb.domain.repositories.UserRepository;
import com.lufegaba.bnb.infraestructure.exceptions.IdNotFoundException;
import com.lufegaba.bnb.infraestructure.utilities.Tables;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ReservationService {

    private final UserRepository userRepository;
    private final LodgingRepository lodgingRepository;
    private final ReservationRepository reservationRepository;

    public Reservation createReservation (Integer userId, Integer lodgingId) {
        var guest = userRepository.findById(userId)
                .orElseThrow(() -> new IdNotFoundException(Tables.users.name()));
        var lodging = lodgingRepository.findById(lodgingId)
                .orElseThrow(() -> new IdNotFoundException(Tables.lodging.name()));
        var newReservation = reservationRepository
                .save(new Reservation(guest, lodging));
        return newReservation;
    }

    public List<Reservation> showUserReservations (Integer userId) {
        var guest = userRepository.findById(userId)
                .orElseThrow(() -> new IdNotFoundException(Tables.users.name()));
        return reservationRepository.findByGuest(guest);
    }

    public List<Reservation> showLodgingReservations (Integer lodgingId) {
        var lodging = lodgingRepository.findById(lodgingId)
                .orElseThrow(() -> new IdNotFoundException(Tables.lodging.name()));
        return reservationRepository.findByLodgingReserved(lodging);
    }

    public Reservation updateReservation (Reservation reservation, Integer userId, Integer reservationId) {
        var reservationToUpdate = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IdNotFoundException(Tables.reservation.name()));
        if (reservation.getCheckIn() != null) reservationToUpdate.setCheckIn(reservation.getCheckIn());
        if (reservation.getCheckOut() != null) reservationToUpdate.setCheckOut(reservation.getCheckOut());
        if (reservation.getHasCanceled() != null) reservationToUpdate.setHasCanceled(reservation.getHasCanceled());
        reservationToUpdate.setUpdatedBy(userRepository.findById(userId)
                .orElseThrow(() -> new IdNotFoundException(Tables.users.name())));
        reservationToUpdate.setUpdatedAt(LocalDateTime.now());
        return reservationToUpdate;
    }

    public void deleteReservation (Integer id) {
        reservationRepository.deleteById(id);
    }

}
