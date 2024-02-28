package org.bessam.mylocallibraryws.membershipsubdomain.dataacess;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bessam.mylocallibraryws.common.MemberIdentifier;
import org.bessam.mylocallibraryws.common.ReservationIdentifier;

@Entity
@Table(name = "members")
@NoArgsConstructor
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private MemberIdentifier memberIdentifier;

    @Embedded
    private ReservationIdentifier reservationIdentifier;

    private String firstName;

    private String lastName;

    private String email;

    @Embedded
    private Membership membership;

    @Embedded
    private Address address;

}
