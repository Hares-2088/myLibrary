package org.bessam.mylocallibraryws.membershipsubdomain.business;

import org.bessam.mylocallibraryws.membershipsubdomain.presentation.MemberRequestModel;
import org.bessam.mylocallibraryws.membershipsubdomain.presentation.MemberResponseModel;

import java.util.List;

public interface MemberService {
    List<MemberResponseModel> getMembers();
    MemberResponseModel getMember(String memberId);
    MemberResponseModel addMember(MemberRequestModel memberRequestModel);
    MemberResponseModel updateMember(MemberRequestModel updatedMember, String memberId);
    void deleteMember(String memberId);
}
