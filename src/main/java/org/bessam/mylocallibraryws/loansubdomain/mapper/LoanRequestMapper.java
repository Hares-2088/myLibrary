package org.bessam.mylocallibraryws.loansubdomain.mapper;

import org.bessam.mylocallibraryws.common.BookIdentifier;
import org.bessam.mylocallibraryws.common.LoanIdentifier;
import org.bessam.mylocallibraryws.common.MemberIdentifier;
import org.bessam.mylocallibraryws.common.ReservationIdentifier;
import org.bessam.mylocallibraryws.loansubdomain.dataacess.Loan;
import org.bessam.mylocallibraryws.loansubdomain.dataacess.LoanPeriod;
import org.bessam.mylocallibraryws.loansubdomain.presentation.LoanRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanRequestMapper {

    @Mapping(target = "id", ignore = true)
    Loan requestModelToEntity(LoanRequestModel loanRequestModel, LoanIdentifier loanIdentifier,
                              MemberIdentifier memberIdentifier, BookIdentifier bookIdentifier,
                              ReservationIdentifier reservationIdentifier, LoanPeriod loanPeriod);
}
