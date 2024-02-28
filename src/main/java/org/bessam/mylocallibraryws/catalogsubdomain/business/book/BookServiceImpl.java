package org.bessam.mylocallibraryws.catalogsubdomain.business.book;

import lombok.extern.slf4j.Slf4j;
import org.bessam.mylocallibraryws.catalogsubdomain.dataacess.author.Author;
import org.bessam.mylocallibraryws.catalogsubdomain.dataacess.author.AuthorRepository;
import org.bessam.mylocallibraryws.catalogsubdomain.dataacess.book.Book;
import org.bessam.mylocallibraryws.catalogsubdomain.dataacess.book.BookRepository;
import org.bessam.mylocallibraryws.catalogsubdomain.mapper.book.BookRequestMapper;
import org.bessam.mylocallibraryws.catalogsubdomain.mapper.book.BookResponseMapper;
import org.bessam.mylocallibraryws.catalogsubdomain.presentation.book.BookRequestModel;
import org.bessam.mylocallibraryws.catalogsubdomain.presentation.book.BookResponseModel;
import org.bessam.mylocallibraryws.common.AuthorIdentifier;
import org.bessam.mylocallibraryws.common.BookIdentifier;
import org.bessam.mylocallibraryws.utils.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookResponseMapper bookResponseMapper;
    private final BookRequestMapper bookRequestMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, BookResponseMapper bookResponseMapper, BookRequestMapper bookRequestMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookResponseMapper = bookResponseMapper;
        this.bookRequestMapper = bookRequestMapper;
    }

    @Override
    public List<BookResponseModel> getBooks() {
        List<Book> books = bookRepository.findAll();
        return bookResponseMapper.entityListToResponseModelList(books);
    }

    @Override
    public BookResponseModel getBook(String bookId) {
        Book book = bookRepository.findByBookIdentifier_BookId(bookId);

        if (book == null) {
            throw new NotFoundException("Unknown bookId: " + bookId);
        }

        return bookResponseMapper.entityToResponseModel(book);
    }

    @Override
    public BookResponseModel createBook(BookRequestModel bookRequestModel) {

        Book book = bookRequestMapper.requestModelToEntity(bookRequestModel, new AuthorIdentifier(bookRequestModel.getAuthorId()), new BookIdentifier() );

        return bookResponseMapper.entityToResponseModel(bookRepository.save(book));
    }

    @Override
    public BookResponseModel updateBook(String bookId, BookRequestModel bookRequestModel) {
        Book foundBook = bookRepository.findByBookIdentifier_BookId(bookId);
        Author author = authorRepository.findByAuthorIdentifier_AuthorId(bookRequestModel.getAuthorId());

        if (author == null) {
            throw new NotFoundException("Unknown authorId: " + bookRequestModel.getAuthorId());
        }
        if (foundBook == null) {
            throw new NotFoundException("Unknown bookId: " + bookId);
        }

        Book book = bookRequestMapper.requestModelToEntity(bookRequestModel, author.getAuthorIdentifier(), foundBook.getBookIdentifier());
        book.setId(foundBook.getId());

        return bookResponseMapper.entityToResponseModel(bookRepository.save(book));
    }

    @Override
    public void deleteBook(String bookId) {
        Book book = bookRepository.findByBookIdentifier_BookId(bookId);

        if (book == null) {
            throw new NotFoundException("Unknown bookId: " + bookId);
        }

        bookRepository.delete(book);
    }
}
