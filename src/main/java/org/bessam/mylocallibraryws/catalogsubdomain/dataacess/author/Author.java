package org.bessam.mylocallibraryws.catalogsubdomain.dataacess.author;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bessam.mylocallibraryws.common.AuthorIdentifier;

@Data
@NoArgsConstructor
@Entity
@Table(name="authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // private id

    private AuthorIdentifier authorIdentifier;

    private String firstName;

    private String lastName;

    private String biography;

    @Embedded
    private Nationality nationality;

    public Author(AuthorIdentifier authorIdentifier, String firstName, String lastName, String biography, Nationality nationality) {
        this.authorIdentifier = authorIdentifier;
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.nationality = nationality;
    }
}
