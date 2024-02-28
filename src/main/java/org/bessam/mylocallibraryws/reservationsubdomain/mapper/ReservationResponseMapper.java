package org.bessam.mylocallibraryws.reservationsubdomain.mapper;

import org.bessam.mylocallibraryws.catalogsubdomain.dataacess.book.Book;
import org.bessam.mylocallibraryws.catalogsubdomain.presentation.book.BookResponseModel;
import org.bessam.mylocallibraryws.membershipsubdomain.dataacess.Member;
import org.bessam.mylocallibraryws.membershipsubdomain.presentation.MemberResponseModel;
import org.bessam.mylocallibraryws.reservationsubdomain.dataacess.Reservation;
import org.bessam.mylocallibraryws.reservationsubdomain.presentation.ReservationController;
import org.bessam.mylocallibraryws.reservationsubdomain.presentation.ReservationResponseModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Mapper(componentModel = "spring")
public interface ReservationResponseMapper {

    @Mapping(expression = "java(reservation.getReservationIdentifier().getReservationId())", target = "reservationId")
    @Mapping(source = "bookResponseModel", target = "book")
    @Mapping(source = "memberResponseModel", target = "member")
    ReservationResponseModel entityToResponseModel(Reservation reservation, BookResponseModel bookResponseModel, MemberResponseModel memberResponseModel, Book book, Member member);

    @AfterMapping
    default void addLinks(@MappingTarget ReservationResponseModel model, Reservation reservation, Book book, Member member) {
        Link selfLink = linkTo(methodOn(ReservationController.class).
                getReservation(member.getMemberIdentifier().getMemberId(), reservation.getReservationIdentifier().getReservationId())).withSelfRel();
        model.add(selfLink);

        Link memberLink = linkTo(methodOn(ReservationController.class).
                getReservations(member.getMemberIdentifier().getMemberId())).withRel("all reservations");
        model.add(memberLink);

        Link bookLink = linkTo(methodOn(ReservationController.class).
                getReservation(member.getMemberIdentifier().getMemberId(), reservation.getReservationIdentifier().getReservationId())).withRel("book");
        model.add(bookLink);
    }
}
