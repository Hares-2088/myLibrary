package org.bessam.mylocallibraryws.catalogsubdomain.dataacess.book;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bessam.mylocallibraryws.common.AuthorIdentifier;
import org.bessam.mylocallibraryws.common.BookIdentifier;

import java.util.Date;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Setter
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // private id

    @Embedded
    private BookIdentifier bookIdentifier;

    @Embedded
    private AuthorIdentifier authorIdentifier;

    private String	title;

    private Date publicationYear;

    private String genre;

    private String description;

    private Integer availableCopies;
}
