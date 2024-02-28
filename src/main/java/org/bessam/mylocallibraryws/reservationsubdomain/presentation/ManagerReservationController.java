package org.bessam.mylocallibraryws.reservationsubdomain.presentation;

import org.bessam.mylocallibraryws.reservationsubdomain.business.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
public class ManagerReservationController {

    ReservationService reservationService;

    @Autowired
    public ManagerReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ReservationResponseModel>> getAllReservations() {
        return ResponseEntity.status(HttpStatus.FOUND).body(reservationService.getAllReservations());
    }

    @GetMapping(value = "/{reservationId}", produces = "application/json")
    public ResponseEntity<ReservationResponseModel> getReservationByReservationId(@PathVariable String reservationId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(reservationService.getReservationByReservationId(reservationId));
    }
}
