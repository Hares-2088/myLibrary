package org.bessam.mylocallibraryws.catalogsubdomain.business.author;

import org.bessam.mylocallibraryws.catalogsubdomain.presentation.author.AuthorRequestModel;
import org.bessam.mylocallibraryws.catalogsubdomain.presentation.author.AuthorResponseModel;
import org.bessam.mylocallibraryws.catalogsubdomain.presentation.book.BookResponseModel;

import java.util.List;

public interface AuthorService {

    List<AuthorResponseModel> getAuthors();
    List<BookResponseModel> getBooksByAuthor(String authorId);
    BookResponseModel getBookByAuthor(String authorId, String bookId);
    AuthorResponseModel addAuthor(AuthorRequestModel authorRequestModel);
    AuthorResponseModel getAuthor(String authorId);
    AuthorResponseModel updateAuthor(String authorId, AuthorRequestModel authorRequestModel);
    void deleteAuthor(String authorId);
}
