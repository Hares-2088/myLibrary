package org.bessam.mylocallibraryws.catalogsubdomain.mapper.book;

import org.bessam.mylocallibraryws.catalogsubdomain.dataacess.book.Book;
import org.bessam.mylocallibraryws.catalogsubdomain.presentation.book.BookRequestModel;
import org.bessam.mylocallibraryws.common.AuthorIdentifier;
import org.bessam.mylocallibraryws.common.BookIdentifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookRequestMapper {

    @Mapping(target = "id", ignore = true)
    Book requestModelToEntity(BookRequestModel bookRequestModel, AuthorIdentifier authorIdentifier, BookIdentifier bookIdentifier);
}
