package org.bessam.mylocallibraryws.catalogsubdomain.presentation.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bessam.mylocallibraryws.common.AuthorIdentifier;
import org.bessam.mylocallibraryws.common.BookIdentifier;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestModel {

    private String authorId;

    private String	title;

    private Date publicationYear;

    private String genre;

    private String description;

    private Integer availableCopies;
}
