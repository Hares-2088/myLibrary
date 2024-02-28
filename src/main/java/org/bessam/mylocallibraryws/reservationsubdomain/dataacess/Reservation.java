package org.bessam.mylocallibraryws.reservationsubdomain.dataacess;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bessam.mylocallibraryws.common.BookIdentifier;
import org.bessam.mylocallibraryws.common.MemberIdentifier;
import org.bessam.mylocallibraryws.common.ReservationIdentifier;

import java.util.Date;

@Entity
@Table(name = "reservations")
@NoArgsConstructor
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private ReservationIdentifier reservationIdentifier;

    @Embedded
    private MemberIdentifier memberIdentifier;

    @Embedded
    private BookIdentifier bookIdentifier;

    private Date reservationDate;
}
