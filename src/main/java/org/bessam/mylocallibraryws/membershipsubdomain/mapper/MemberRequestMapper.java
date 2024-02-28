package org.bessam.mylocallibraryws.membershipsubdomain.mapper;

import org.bessam.mylocallibraryws.common.MemberIdentifier;
import org.bessam.mylocallibraryws.common.ReservationIdentifier;
import org.bessam.mylocallibraryws.membershipsubdomain.dataacess.Address;
import org.bessam.mylocallibraryws.membershipsubdomain.dataacess.Member;
import org.bessam.mylocallibraryws.membershipsubdomain.dataacess.Membership;
import org.bessam.mylocallibraryws.membershipsubdomain.presentation.MemberRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberRequestMapper {

    @Mapping(target = "id", ignore = true)
    Member requestModelToEntity(MemberRequestModel memberRequestModel, MemberIdentifier memberIdentifier,
                                ReservationIdentifier reservationIdentifier, Address address,
                                Membership membership);
}
