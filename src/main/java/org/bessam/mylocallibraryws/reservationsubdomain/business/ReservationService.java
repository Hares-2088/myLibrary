package org.bessam.mylocallibraryws.reservationsubdomain.business;

import org.bessam.mylocallibraryws.reservationsubdomain.presentation.ReservationRequestModel;
import org.bessam.mylocallibraryws.reservationsubdomain.presentation.ReservationResponseModel;

import java.util.List;

public interface ReservationService {

    List<ReservationResponseModel> getAllReservations();
    ReservationResponseModel getReservationByReservationId(String reservationId);
    List<ReservationResponseModel> getReservations(String memberId);
    ReservationResponseModel getReservation(String reservationId, String memberId);
    ReservationResponseModel createReservation(ReservationRequestModel reservationRequestModel, String memberId);
    ReservationResponseModel updateReservation(ReservationRequestModel reservationRequestModel, String reservationId, String memberId);
    void deleteReservation(String reservationId, String memberId);
}
