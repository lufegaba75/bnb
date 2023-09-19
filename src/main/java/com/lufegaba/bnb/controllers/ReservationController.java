package com.lufegaba.bnb.controllers;

import com.lufegaba.bnb.domain.Reservation;
import com.lufegaba.bnb.infraestructure.services.LodgingService;
import com.lufegaba.bnb.infraestructure.services.ReservationService;
import com.lufegaba.bnb.infraestructure.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/users/{userId}/reservations")
@AllArgsConstructor
public class ReservationController {

    private final UserService userService;
    private final LodgingService lodgingService;
    private final ReservationService reservationService;

    @PostMapping ("/{lodgingId}")
    public ResponseEntity<Reservation> createNewReservation (
            @PathVariable Integer userId,
            @PathVariable Integer lodgingId,
            @RequestBody @Valid Reservation reservation) {
        var reservationCreated = reservationService.createReservation(userId, lodgingId);

        return ResponseEntity.ok(reservationCreated);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> showUserReservations (@PathVariable Integer userId) {
        var reservations = reservationService.showUserReservations(userId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{lodgingId}")
    public ResponseEntity<List<Reservation>> showLodgingReservations (@PathVariable Integer lodgingId) {
        var reservations = reservationService.showLodgingReservations(lodgingId);
        return ResponseEntity.ok(reservations);
    }

    @PatchMapping("/{reservationId}")
    public ResponseEntity<Reservation> updateReservation (
            @PathVariable Integer reservationId,
            @PathVariable Integer userId,
            @RequestBody @Valid Reservation reservation) {
        var reservationUpdated = reservationService.updateReservation(reservation, reservationId, userId);
        return ResponseEntity.ok(reservationUpdated);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation (@PathVariable Integer reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.noContent().build();
    }

}
