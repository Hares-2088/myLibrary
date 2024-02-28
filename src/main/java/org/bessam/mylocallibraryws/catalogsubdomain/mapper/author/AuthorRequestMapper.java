package org.bessam.mylocallibraryws.catalogsubdomain.mapper.author;

import org.bessam.mylocallibraryws.catalogsubdomain.dataacess.author.Author;
import org.bessam.mylocallibraryws.catalogsubdomain.dataacess.author.Nationality;
import org.bessam.mylocallibraryws.catalogsubdomain.presentation.author.AuthorRequestModel;
import org.bessam.mylocallibraryws.common.AuthorIdentifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorRequestMapper {

    @Mapping(target = "id", ignore = true)
    Author requestModelToEntity(AuthorRequestModel authorRequestModel, AuthorIdentifier authorIdentifier, Nationality nationality);
}
