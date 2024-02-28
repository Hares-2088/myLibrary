package org.bessam.mylocallibraryws.reservationsubdomain.presentation;

import lombok.extern.slf4j.Slf4j;
import org.bessam.mylocallibraryws.reservationsubdomain.business.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/members/{memberId}/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ReservationResponseModel>> getReservations(@PathVariable String memberId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(reservationService.getReservations(memberId));
    }

    @GetMapping(value = "{reservationId}", produces = "application/json")
    public ResponseEntity<ReservationResponseModel> getReservation(@PathVariable String reservationId, @PathVariable String memberId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(reservationService.getReservation(reservationId, memberId));
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<ReservationResponseModel> createReservation(@RequestBody ReservationRequestModel reservationRequestModel, @PathVariable String memberId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.createReservation(reservationRequestModel, memberId));
    }

    @PutMapping(value = "{reservationId}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ReservationResponseModel> updateReservation(@RequestBody ReservationRequestModel reservationRequestModel, @PathVariable String reservationId, @PathVariable String memberId) {
        return ResponseEntity.ok().body(reservationService.updateReservation(reservationRequestModel, reservationId, memberId));
    }

    @DeleteMapping(value = "{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable String reservationId, @PathVariable String memberId) {
        reservationService.deleteReservation(reservationId, memberId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
