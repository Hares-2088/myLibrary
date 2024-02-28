package org.bessam.mylocallibraryws.membershipsubdomain.business;

import org.bessam.mylocallibraryws.common.MemberIdentifier;
import org.bessam.mylocallibraryws.membershipsubdomain.dataacess.Address;
import org.bessam.mylocallibraryws.membershipsubdomain.dataacess.Member;
import org.bessam.mylocallibraryws.membershipsubdomain.dataacess.MemberRepository;
import org.bessam.mylocallibraryws.membershipsubdomain.dataacess.Membership;
import org.bessam.mylocallibraryws.membershipsubdomain.mapper.MemberRequestMapper;
import org.bessam.mylocallibraryws.membershipsubdomain.mapper.MemberResponseMapper;
import org.bessam.mylocallibraryws.membershipsubdomain.presentation.MemberRequestModel;
import org.bessam.mylocallibraryws.membershipsubdomain.presentation.MemberResponseModel;
import org.bessam.mylocallibraryws.reservationsubdomain.dataacess.Reservation;
import org.bessam.mylocallibraryws.reservationsubdomain.dataacess.ReservationRepository;
import org.bessam.mylocallibraryws.utils.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final MemberRequestMapper memberRequestMapper;
    private final MemberResponseMapper memberResponseMapper;
    private final ReservationRepository reservationRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, MemberRequestMapper memberRequestMapper, MemberResponseMapper memberResponseMapper, ReservationRepository reservationRepository) {
        this.memberRepository = memberRepository;
        this.memberRequestMapper = memberRequestMapper;
        this.memberResponseMapper = memberResponseMapper;
        this.reservationRepository = reservationRepository;
    }


    @Override
    public List<MemberResponseModel> getMembers() {
        return memberResponseMapper.entityListToResponseModelList(memberRepository.findAll());
    }

    @Override
    public MemberResponseModel getMember(String memberId) {
        Member member = memberRepository.findByMemberIdentifier_MemberId(memberId);
        if (member == null) {
            throw new NotFoundException("Unknown memberId: " + memberId);
        }

        return memberResponseMapper.entityToResponseModel(member);
    }

    @Override
    public MemberResponseModel addMember(MemberRequestModel memberRequestModel) {

        Reservation reservation = reservationRepository.findByReservationIdentifier_ReservationId(memberRequestModel.getReservationId());
        if (reservation == null) {
            throw new NotFoundException("Unknown reservationId: " + memberRequestModel.getReservationId());
        }

        Membership membership = new Membership(memberRequestModel.getBenefits(), memberRequestModel.getMemberType());

        Member member = memberRequestMapper.requestModelToEntity(memberRequestModel, new MemberIdentifier(), reservation.getReservationIdentifier(),
                new Address(memberRequestModel.getStreet(), memberRequestModel.getCity(), memberRequestModel.getProvince(), memberRequestModel.getCountry()), membership);
        return memberResponseMapper.entityToResponseModel(memberRepository.save(member));
    }

    @Override
    public MemberResponseModel updateMember(MemberRequestModel memberRequestModel, String memberId) {
        Member member = memberRepository.findByMemberIdentifier_MemberId(memberId);
        if (member == null) {
            throw new NotFoundException("Unknown memberId: " + memberId);
        }

        Reservation reservation = reservationRepository.findByReservationIdentifier_ReservationId(memberRequestModel.getReservationId());
        if (reservation == null) {
            throw new NotFoundException("Unknown reservationId: " + memberRequestModel.getReservationId());
        }

        Member updatedMember = memberRequestMapper.requestModelToEntity(memberRequestModel, member.getMemberIdentifier(), reservation.getReservationIdentifier(),
                new Address(memberRequestModel.getStreet(), memberRequestModel.getCity(), memberRequestModel.getProvince(), memberRequestModel.getCountry()), new Membership(memberRequestModel.getBenefits(), memberRequestModel.getMemberType()));
        updatedMember.setId(member.getId());

        return memberResponseMapper.entityToResponseModel(memberRepository.save(updatedMember));
    }

    @Override
    public void deleteMember(String memberId) {
        Member member = memberRepository.findByMemberIdentifier_MemberId(memberId);

        if (member == null) {
            throw new NotFoundException("Unknown memberId: " + memberId);
        }
        memberRepository.delete(member);
    }
}
