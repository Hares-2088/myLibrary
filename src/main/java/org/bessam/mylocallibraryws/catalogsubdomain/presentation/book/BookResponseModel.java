package org.bessam.mylocallibraryws.catalogsubdomain.presentation.book;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookResponseModel extends RepresentationModel<BookResponseModel> {


    private String bookId;

    private String authorId;

    private String	title;

    private Date publicationYear;

    private String genre;

    private String description;

    private Integer availableCopies;

}
