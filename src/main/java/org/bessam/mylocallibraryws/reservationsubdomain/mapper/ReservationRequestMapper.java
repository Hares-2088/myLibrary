package org.bessam.mylocallibraryws.reservationsubdomain.mapper;

import org.bessam.mylocallibraryws.common.BookIdentifier;
import org.bessam.mylocallibraryws.common.MemberIdentifier;
import org.bessam.mylocallibraryws.common.ReservationIdentifier;
import org.bessam.mylocallibraryws.reservationsubdomain.dataacess.Reservation;
import org.bessam.mylocallibraryws.reservationsubdomain.presentation.ReservationRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationRequestMapper {

    @Mapping(target = "id", ignore = true)
    Reservation requestModelToEntity(ReservationRequestModel reservationRequestModel, ReservationIdentifier reservationIdentifier,
                                     BookIdentifier bookIdentifier, MemberIdentifier memberIdentifier);
}
