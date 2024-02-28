package org.bessam.mylocallibraryws.catalogsubdomain.mapper.book;

import org.bessam.mylocallibraryws.catalogsubdomain.dataacess.book.Book;
import org.bessam.mylocallibraryws.catalogsubdomain.presentation.book.BookController;
import org.bessam.mylocallibraryws.catalogsubdomain.presentation.book.BookResponseModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface BookResponseMapper {

    @Mapping(expression = "java(book.getBookIdentifier().getBookId())", target = "bookId")
    @Mapping(expression = "java(book.getAuthorIdentifier().getAuthorId())", target = "authorId")
    BookResponseModel entityToResponseModel(Book book);

    List<BookResponseModel> entityListToResponseModelList(List<Book> books);

    @AfterMapping
    default void addLinks(@MappingTarget BookResponseModel model, Book book) {
        Link selfLink = linkTo(methodOn(BookController.class).
                getBook(model.getBookId())).withSelfRel();
        model.add(selfLink);
    }

}
