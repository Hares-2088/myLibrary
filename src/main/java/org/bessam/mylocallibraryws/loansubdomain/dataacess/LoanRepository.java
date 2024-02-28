package org.bessam.mylocallibraryws.loansubdomain.dataacess;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

    Loan findLoanByLoanIdentifier_LoanIdAndMemberIdentifier_MemberId(String loanId, String memberId);

    List<Loan> findAllByMemberIdentifier_MemberId(String memberId);

}
