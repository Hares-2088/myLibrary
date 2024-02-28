package org.bessam.mylocallibraryws.loansubdomain.dataacess;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bessam.mylocallibraryws.common.BookIdentifier;
import org.bessam.mylocallibraryws.common.LoanIdentifier;
import org.bessam.mylocallibraryws.common.MemberIdentifier;
import org.bessam.mylocallibraryws.common.ReservationIdentifier;

@Entity
@Table(name = "loans")
@NoArgsConstructor
@Data
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private LoanIdentifier loanIdentifier;

    @Embedded
    private MemberIdentifier memberIdentifier;

    @Embedded
    private BookIdentifier bookIdentifier;

    @Embedded
    private ReservationIdentifier reservationIdentifier;

    private Boolean returned;

    @Embedded
    private LoanPeriod loanPeriod;

    public Loan(MemberIdentifier memberIdentifier, BookIdentifier bookIdentifier, ReservationIdentifier reservationIdentifier, Boolean returned, LoanPeriod loanPeriod) {
        this.loanIdentifier = new LoanIdentifier();
        this.memberIdentifier = memberIdentifier;
        this.bookIdentifier = bookIdentifier;
        this.reservationIdentifier = reservationIdentifier;
        this.returned = returned;
        this.loanPeriod = loanPeriod;
    }

}
