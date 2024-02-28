package org.bessam.mylocallibraryws.reservationsubdomain.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bessam.mylocallibraryws.catalogsubdomain.presentation.book.BookResponseModel;
import org.bessam.mylocallibraryws.membershipsubdomain.presentation.MemberResponseModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseModel extends RepresentationModel<ReservationResponseModel> {

    private String reservationId;

    private BookResponseModel book;

    private MemberResponseModel member;

    private Date reservationDate;
}
