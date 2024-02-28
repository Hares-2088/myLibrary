package org.bessam.mylocallibraryws.reservationsubdomain.business;

import org.bessam.mylocallibraryws.catalogsubdomain.dataacess.book.Book;
import org.bessam.mylocallibraryws.catalogsubdomain.dataacess.book.BookRepository;
import org.bessam.mylocallibraryws.catalogsubdomain.mapper.book.BookResponseMapper;
import org.bessam.mylocallibraryws.catalogsubdomain.presentation.book.BookResponseModel;
import org.bessam.mylocallibraryws.common.ReservationIdentifier;
import org.bessam.mylocallibraryws.membershipsubdomain.dataacess.Member;
import org.bessam.mylocallibraryws.membershipsubdomain.dataacess.MemberRepository;
import org.bessam.mylocallibraryws.membershipsubdomain.mapper.MemberResponseMapper;
import org.bessam.mylocallibraryws.membershipsubdomain.presentation.MemberResponseModel;
import org.bessam.mylocallibraryws.reservationsubdomain.dataacess.Reservation;
import org.bessam.mylocallibraryws.reservationsubdomain.dataacess.ReservationRepository;
import org.bessam.mylocallibraryws.reservationsubdomain.mapper.ReservationRequestMapper;
import org.bessam.mylocallibraryws.reservationsubdomain.mapper.ReservationResponseMapper;
import org.bessam.mylocallibraryws.reservationsubdomain.presentation.ReservationRequestModel;
import org.bessam.mylocallibraryws.reservationsubdomain.presentation.ReservationResponseModel;
import org.bessam.mylocallibraryws.utils.exceptions.NotEnoughCopiesException;
import org.bessam.mylocallibraryws.utils.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationRequestMapper reservationRequestMapper;
    private final ReservationResponseMapper reservationResponseMapper;
    private final BookRepository bookRepository;
    private final BookResponseMapper bookResponseMapper;
    private final MemberRepository memberRepository;
    private final MemberResponseMapper memberResponseMapper;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationRequestMapper reservationRequestMapper, ReservationResponseMapper reservationResponseMapper, BookRepository bookRepository, BookResponseMapper bookResponseMapper, MemberRepository memberRepository, MemberResponseMapper memberResponseMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationRequestMapper = reservationRequestMapper;
        this.reservationResponseMapper = reservationResponseMapper;
        this.bookRepository = bookRepository;
        this.bookResponseMapper = bookResponseMapper;
        this.memberRepository = memberRepository;
        this.memberResponseMapper = memberResponseMapper;
    }

    @Override
    public List<ReservationResponseModel> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        List<ReservationResponseModel> reservationResponseModels = new ArrayList<>();

        for (Reservation reservation : reservations) {
            BookResponseModel bookResponseModel = bookResponseMapper.entityToResponseModel(bookRepository.findByBookIdentifier_BookId(reservation.getBookIdentifier().getBookId()));
            Book book = bookRepository.findByBookIdentifier_BookId(reservation.getBookIdentifier().getBookId());

            MemberResponseModel memberResponseModel = memberResponseMapper.entityToResponseModel(memberRepository.findByMemberIdentifier_MemberId(reservation.getMemberIdentifier().getMemberId()));
            Member member = memberRepository.findByMemberIdentifier_MemberId(reservation.getMemberIdentifier().getMemberId());

            if (book != null && member != null) {
                reservationResponseModels.add(reservationResponseMapper.entityToResponseModel(reservation, bookResponseModel, memberResponseModel, bookRepository.findByBookIdentifier_BookId(reservation.getBookIdentifier().getBookId()), memberRepository.findByMemberIdentifier_MemberId(reservation.getMemberIdentifier().getMemberId())));
            }
        }
        return reservationResponseModels;
    }

    @Override
    public ReservationResponseModel getReservationByReservationId(String reservationId) {
        Reservation reservation = reservationRepository.findByReservationIdentifier_ReservationId(reservationId);
        if (reservation == null) {
            throw new NotFoundException("Unknown reservationId: " + reservationId);
        }

        BookResponseModel bookResponseModel = bookResponseMapper.entityToResponseModel(bookRepository.findByBookIdentifier_BookId(reservation.getBookIdentifier().getBookId()));
        MemberResponseModel memberResponseModel = memberResponseMapper.entityToResponseModel(memberRepository.findByMemberIdentifier_MemberId(reservation.getMemberIdentifier().getMemberId()));

        return reservationResponseMapper.entityToResponseModel(reservation, bookResponseModel, memberResponseModel, bookRepository.findByBookIdentifier_BookId(reservation.getBookIdentifier().getBookId()), memberRepository.findByMemberIdentifier_MemberId(reservation.getMemberIdentifier().getMemberId()));
    }

    @Override
    public List<ReservationResponseModel> getReservations(String memberId) {
        List<Reservation> reservations = reservationRepository.findAllByMemberIdentifier_MemberId(memberId);

        List<ReservationResponseModel> reservationResponseModels = new ArrayList<>();

        for (Reservation reservation : reservations) {
            BookResponseModel bookResponseModel = bookResponseMapper.entityToResponseModel(bookRepository.findByBookIdentifier_BookId(reservation.getBookIdentifier().getBookId()));
            MemberResponseModel memberResponseModel = memberResponseMapper.entityToResponseModel(memberRepository.findByMemberIdentifier_MemberId(memberId));
            reservationResponseModels.add(reservationResponseMapper.entityToResponseModel(reservation, bookResponseModel, memberResponseModel, bookRepository.findByBookIdentifier_BookId(reservation.getBookIdentifier().getBookId()), memberRepository.findByMemberIdentifier_MemberId(memberId)));
        }
        return reservationResponseModels;
    }

    @Override
    public ReservationResponseModel getReservation(String reservationId, String memberId) {
        Reservation reservation = reservationRepository.findByReservationIdentifier_ReservationIdAndMemberIdentifier_MemberId(reservationId, memberId);
        if (reservation == null) {
            throw new NotFoundException("Unknown reservationId: " + reservationId);
        }

        BookResponseModel bookResponseModel = bookResponseMapper.entityToResponseModel(bookRepository.findByBookIdentifier_BookId(reservation.getBookIdentifier().getBookId()));
        MemberResponseModel memberResponseModel = memberResponseMapper.entityToResponseModel(memberRepository.findByMemberIdentifier_MemberId(memberId));

        return reservationResponseMapper.entityToResponseModel(reservation, bookResponseModel, memberResponseModel, bookRepository.findByBookIdentifier_BookId(reservation.getBookIdentifier().getBookId()), memberRepository.findByMemberIdentifier_MemberId(memberId));
    }

    @Override
    public ReservationResponseModel createReservation(ReservationRequestModel reservationRequestModel, String memberId) {
        Book book = bookRepository.findByBookIdentifier_BookId(reservationRequestModel.getBookId());
        if (book == null) {
            throw new NotFoundException("Unknown bookId: " + reservationRequestModel.getBookId());
        }
        // Update the book available copies
        book.setAvailableCopies(book.getAvailableCopies() - 1);

        if (book.getAvailableCopies() <= 0) {
            throw new NotEnoughCopiesException("No available copies for bookId: " + reservationRequestModel.getBookId());
        }

        Member member = memberRepository.findByMemberIdentifier_MemberId(memberId);
        if (member == null) {
            throw new NotFoundException("Unknown memberId: " + memberId);
        }

        Reservation reservation = reservationRequestMapper.requestModelToEntity(reservationRequestModel, new ReservationIdentifier(), book.getBookIdentifier(), member.getMemberIdentifier());

        return reservationResponseMapper.entityToResponseModel(reservationRepository.save(reservation), bookResponseMapper.entityToResponseModel(book), memberResponseMapper.entityToResponseModel(member), book, member);
    }

    @Override
    public ReservationResponseModel updateReservation(ReservationRequestModel reservationRequestModel, String reservationId, String memberId) {
        Reservation reservation = reservationRepository.findByReservationIdentifier_ReservationIdAndMemberIdentifier_MemberId(reservationId, memberId);
        if (reservation == null) {
            throw new NotFoundException("Unknown reservationId: " + reservationId);
        }

        // Update the book available copies
        Book previousBook = bookRepository.findByBookIdentifier_BookId(reservation.getBookIdentifier().getBookId());

        Book newBook = bookRepository.findByBookIdentifier_BookId(reservationRequestModel.getBookId());
        if (newBook == null) {
            throw new NotFoundException("Unknown bookId: " + reservationRequestModel.getBookId());
        }
        if(!Objects.equals(reservation.getBookIdentifier().getBookId(), reservationRequestModel.getBookId())) {
            newBook.setAvailableCopies(newBook.getAvailableCopies() - 1);
            previousBook.setAvailableCopies(previousBook.getAvailableCopies() + 1);
        }

        Member member = memberRepository.findByMemberIdentifier_MemberId(memberId);

        Reservation updatedReservation = reservationRequestMapper.requestModelToEntity(reservationRequestModel, reservation.getReservationIdentifier(), newBook.getBookIdentifier(), member.getMemberIdentifier());
        updatedReservation.setId(reservation.getId());

        return reservationResponseMapper.entityToResponseModel(reservationRepository.save(updatedReservation), bookResponseMapper.entityToResponseModel(newBook), memberResponseMapper.entityToResponseModel(member), newBook, member);
    }

    @Override
    public void deleteReservation(String reservationId, String memberId) {

        Reservation reservation = reservationRepository.findByReservationIdentifier_ReservationIdAndMemberIdentifier_MemberId(reservationId, memberId);
        if (reservation == null) {
            throw new NotFoundException("Unknown reservationId: " + reservationId);
        }
        // Update the book available copies
        Book book = bookRepository.findByBookIdentifier_BookId(reservation.getBookIdentifier().getBookId());
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        reservationRepository.delete(reservation);

    }
}
