package org.bessam.mylocallibraryws.loansubdomain.business;

import org.bessam.mylocallibraryws.catalogsubdomain.dataacess.book.Book;
import org.bessam.mylocallibraryws.catalogsubdomain.dataacess.book.BookRepository;
import org.bessam.mylocallibraryws.catalogsubdomain.mapper.book.BookResponseMapper;
import org.bessam.mylocallibraryws.catalogsubdomain.presentation.book.BookResponseModel;
import org.bessam.mylocallibraryws.common.LoanIdentifier;
import org.bessam.mylocallibraryws.loansubdomain.dataacess.Loan;
import org.bessam.mylocallibraryws.loansubdomain.dataacess.LoanPeriod;
import org.bessam.mylocallibraryws.loansubdomain.dataacess.LoanRepository;
import org.bessam.mylocallibraryws.loansubdomain.mapper.LoanRequestMapper;
import org.bessam.mylocallibraryws.loansubdomain.mapper.LoanResponseMapper;
import org.bessam.mylocallibraryws.loansubdomain.presentation.LoanRequestModel;
import org.bessam.mylocallibraryws.loansubdomain.presentation.LoanResponseModel;
import org.bessam.mylocallibraryws.membershipsubdomain.dataacess.Member;
import org.bessam.mylocallibraryws.membershipsubdomain.dataacess.MemberRepository;
import org.bessam.mylocallibraryws.reservationsubdomain.dataacess.Reservation;
import org.bessam.mylocallibraryws.reservationsubdomain.dataacess.ReservationRepository;
import org.bessam.mylocallibraryws.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService{

    private final LoanRepository loanRepository;
    private final LoanResponseMapper loanResponseMapper;
    private final LoanRequestMapper loanRequestMapper;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final ReservationRepository reservationRepository;
    private final BookResponseMapper bookResponseMapper;

    public LoanServiceImpl(LoanRepository loanRepository, LoanResponseMapper loanResponseMapper, LoanRequestMapper loanRequestMapper, MemberRepository memberRepository, BookRepository bookRepository, ReservationRepository reservationRepository, BookResponseMapper bookResponseMapper) {
        this.loanRepository = loanRepository;
        this.loanResponseMapper = loanResponseMapper;
        this.loanRequestMapper = loanRequestMapper;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.reservationRepository = reservationRepository;
        this.bookResponseMapper = bookResponseMapper;
    }

    @Override
    public List<LoanResponseModel> getLoans(String memberId) {

        List<LoanResponseModel> loanResponseModels = new ArrayList<>();

        for (Loan loan : loanRepository.findAllByMemberIdentifier_MemberId(memberId)) {
            Member member = memberRepository.findByMemberIdentifier_MemberId(memberId);
            if ( member == null) {
                throw new NotFoundException("Unknown memberId: " + memberId);
            }

            Book book = bookRepository.findByBookIdentifier_BookId(loan.getBookIdentifier().getBookId());
            BookResponseModel bookResponseModel = bookResponseMapper.entityToResponseModel(book);

            Reservation reservation = reservationRepository.findByReservationIdentifier_ReservationId(loan.getReservationIdentifier().getReservationId());

            loanResponseModels.add(loanResponseMapper.entityToResponseModel(loan, member, reservation, bookResponseModel, book));
        }

        return loanResponseModels;
    }

    @Override
    public LoanResponseModel getLoan(String loanId, String memberId) {

        Loan loan = loanRepository.findLoanByLoanIdentifier_LoanIdAndMemberIdentifier_MemberId(loanId, memberId);

        Member member = memberRepository.findByMemberIdentifier_MemberId(memberId);
        if ( member == null) {
            throw new NotFoundException("Unknown memberId: " + memberId);
        }

        Book book = bookRepository.findByBookIdentifier_BookId(loan.getBookIdentifier().getBookId());
        BookResponseModel bookResponseModel = bookResponseMapper.entityToResponseModel(book);

        Reservation reservation = reservationRepository.findByReservationIdentifier_ReservationId(loan.getReservationIdentifier().getReservationId());

        return loanResponseMapper.entityToResponseModel(loan, member, reservation, bookResponseModel, book);
    }

    @Override
    public LoanResponseModel createLoan(LoanRequestModel loanRequestModel, String memberId) {
        Member member = memberRepository.findByMemberIdentifier_MemberId(memberId);
        if ( member == null) {
            throw new NotFoundException("Unknown memberId: " + memberId);
        }

        Book book = bookRepository.findByBookIdentifier_BookId(loanRequestModel.getBookId());
        if ( book == null) {
            throw new NotFoundException("Unknown bookId: " + loanRequestModel.getBookId());
        }

        BookResponseModel bookResponseModel = bookResponseMapper.entityToResponseModel(book);

        Reservation reservation = reservationRepository.findByReservationIdentifier_ReservationId(member.getReservationIdentifier().getReservationId());

        Loan loan = loanRequestMapper.requestModelToEntity(loanRequestModel, new LoanIdentifier(), member.getMemberIdentifier(),
                                                            book.getBookIdentifier(), reservation.getReservationIdentifier(),
                                                            new LoanPeriod());
        return loanResponseMapper.entityToResponseModel(loanRepository.save(loan), member, reservation, bookResponseModel, book);
    }

    @Override
    public LoanResponseModel updateLoan(LoanRequestModel loanRequestModel, String loanId, String memberId) {

        Loan loan = loanRepository.findLoanByLoanIdentifier_LoanIdAndMemberIdentifier_MemberId(loanId, memberId);

        if ( loan == null) {
            throw new NotFoundException("Unknown loanId: " + loanId);
        }

        Member member = memberRepository.findByMemberIdentifier_MemberId(memberId);

        if ( member == null) {
            throw new NotFoundException("Unknown memberId: " + memberId);
        }

        Book book = bookRepository.findByBookIdentifier_BookId(loanRequestModel.getBookId());

        if ( book == null) {
            throw new NotFoundException("Unknown bookId: " + loanRequestModel.getBookId());
        }
        BookResponseModel bookResponseModel = bookResponseMapper.entityToResponseModel(book);

        Reservation reservation = reservationRepository.findByReservationIdentifier_ReservationId(member.getReservationIdentifier().getReservationId());

        Loan updatedLoan = loanRequestMapper.requestModelToEntity(loanRequestModel, loan.getLoanIdentifier(), member.getMemberIdentifier(),
                book.getBookIdentifier(), reservation.getReservationIdentifier(),
                new LoanPeriod());

        updatedLoan.setId(loan.getId());
        return loanResponseMapper.entityToResponseModel(loanRepository.save(updatedLoan), member, reservation, bookResponseModel, book);
    }

    @Override
    public void deleteLoan(String loanId, String memberId) {
        Loan loan = loanRepository.findLoanByLoanIdentifier_LoanIdAndMemberIdentifier_MemberId(loanId, memberId);
        Reservation reservation = reservationRepository.findByReservationIdentifier_ReservationId(loan.getReservationIdentifier().getReservationId());
        Member member = memberRepository.findByMemberIdentifier_MemberId(memberId);

        if ( loan == null) {
            throw new NotFoundException("Unknown loanId: " + loanId);
        }
        if ( member != null) {
            throw new NotFoundException("Unknown memberId: " + memberId);
        }

        reservationRepository.delete(reservation);
        loanRepository.delete(loan);
    }
}
