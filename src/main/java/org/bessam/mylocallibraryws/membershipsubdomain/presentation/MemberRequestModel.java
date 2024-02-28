package org.bessam.mylocallibraryws.membershipsubdomain.presentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bessam.mylocallibraryws.membershipsubdomain.dataacess.MemberType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestModel {

    private String reservationId;

    private String firstName;

    private String lastName;

    private String email;

    private String benefits;

    private MemberType memberType;

    private String street;

    private String city;

    private String province;

    private String country;
}
