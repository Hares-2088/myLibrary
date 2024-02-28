package org.bessam.mylocallibraryws.catalogsubdomain.business.book;

import org.bessam.mylocallibraryws.catalogsubdomain.presentation.book.BookRequestModel;
import org.bessam.mylocallibraryws.catalogsubdomain.presentation.book.BookResponseModel;

import java.util.List;

public interface BookService {

    List<BookResponseModel> getBooks();
    BookResponseModel getBook(String bookId);
    BookResponseModel createBook(BookRequestModel bookRequestModel);
    BookResponseModel updateBook(String bookId, BookRequestModel bookRequestModel);
    void deleteBook(String bookId);

}
