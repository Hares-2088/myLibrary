package org.bessam.mylocallibraryws.loansubdomain.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bessam.mylocallibraryws.catalogsubdomain.presentation.book.BookResponseModel;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoanResponseModel extends RepresentationModel<LoanResponseModel> {

    private String loanId;

    private String memberId;

    private String memberFirstName;

    private String memberLastName;

    private String memberEmail;

    private BookResponseModel book;

    private String reservationId;

    private Boolean returned;

    private LocalDate loanDate;

    private LocalDate dueDate;
}
