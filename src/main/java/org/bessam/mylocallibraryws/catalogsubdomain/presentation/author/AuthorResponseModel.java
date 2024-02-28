package org.bessam.mylocallibraryws.catalogsubdomain.presentation.author;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorResponseModel extends RepresentationModel<AuthorResponseModel> {

    private String authorId;
    private String firstName;
    private String lastName;
    private String biography;
    private String country;
    private String city;
    private String province;

}
