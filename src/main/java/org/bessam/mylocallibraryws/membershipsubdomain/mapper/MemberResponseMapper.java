package org.bessam.mylocallibraryws.membershipsubdomain.mapper;

import org.bessam.mylocallibraryws.membershipsubdomain.dataacess.Member;
import org.bessam.mylocallibraryws.membershipsubdomain.presentation.MemberController;
import org.bessam.mylocallibraryws.membershipsubdomain.presentation.MemberResponseModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface MemberResponseMapper {

    @Mapping(expression = "java(member.getMemberIdentifier().getMemberId())", target = "memberId")
    @Mapping(expression = "java(member.getReservationIdentifier().getReservationId())", target = "reservationId")
    @Mapping(expression = "java(member.getAddress().getStreet())", target = "street")
    @Mapping(expression = "java(member.getAddress().getCity())", target = "city")
    @Mapping(expression = "java(member.getAddress().getProvince())", target = "province")
    @Mapping(expression = "java(member.getAddress().getCountry())", target = "country")
    @Mapping(expression = "java(member.getMembership().getMemberType().toString())", target = "memberType")
    @Mapping(expression = "java(member.getMembership().getBenefits())", target = "benefits")
    MemberResponseModel entityToResponseModel(Member member);

    List<MemberResponseModel> entityListToResponseModelList(List<Member> members);

    @AfterMapping
    default void addLinks(@MappingTarget MemberResponseModel model, Member member) {
        Link selfLink = linkTo(methodOn(MemberController.class).
                getMemberByMemberId(model.getMemberId())).withSelfRel();
        model.add(selfLink);
    }
}
