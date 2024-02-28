package org.bessam.mylocallibraryws.loansubdomain.mapper;

import org.bessam.mylocallibraryws.catalogsubdomain.dataacess.book.Book;
import org.bessam.mylocallibraryws.catalogsubdomain.presentation.book.BookController;
import org.bessam.mylocallibraryws.catalogsubdomain.presentation.book.BookResponseModel;
import org.bessam.mylocallibraryws.loansubdomain.dataacess.Loan;
import org.bessam.mylocallibraryws.loansubdomain.presentation.LoanController;
import org.bessam.mylocallibraryws.loansubdomain.presentation.LoanResponseModel;
import org.bessam.mylocallibraryws.membershipsubdomain.dataacess.Member;
import org.bessam.mylocallibraryws.reservationsubdomain.dataacess.Reservation;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface LoanResponseMapper {

    @Mapping(expression = "java(loan.getLoanIdentifier().getLoanId())", target = "loanId")
    @Mapping(expression = "java(member.getMemberIdentifier().getMemberId())", target = "memberId")
    @Mapping(expression = "java(member.getFirstName())", target = "memberFirstName")
    @Mapping(expression = "java(member.getLastName())", target = "memberLastName")
    @Mapping(expression = "java(member.getEmail())", target = "memberEmail")
    @Mapping(expression = "java(reservation.getReservationIdentifier().getReservationId())", target = "reservationId")
    @Mapping(expression = "java(loan.getLoanPeriod().getLoanDate())", target = "loanDate")
    @Mapping(expression = "java(loan.getLoanPeriod().getDueDate())", target = "dueDate")
    @Mapping(source = "bookResponseModel", target = "book")
    LoanResponseModel entityToResponseModel(Loan loan, Member member, Reservation reservation, BookResponseModel bookResponseModel, Book book);

    @AfterMapping
    default void addLinks(@MappingTarget LoanResponseModel model, Loan loan, Member member, Reservation reservation, BookResponseModel bookResponseModel, Book book) {
        Link selfLink = linkTo(methodOn(LoanController.class).
                getLoan(model.getMemberId(), model.getLoanId())).withSelfRel();
        model.add(selfLink);

        //all loans for member
        Link loansLink =
                linkTo(methodOn(LoanController.class).
                        getLoans(model.getMemberId())).withRel
                        ("all loans");
        model.add(loansLink);

        //book link
        Link bookLink =
                linkTo(methodOn(BookController.class).
                        getBook(bookResponseModel.getBookId())).withRel("book");
        model.add(bookLink);
    }
}
