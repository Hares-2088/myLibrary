package org.bessam.mylocallibraryws.catalogsubdomain.business.author;

import org.bessam.mylocallibraryws.catalogsubdomain.dataacess.author.Author;
import org.bessam.mylocallibraryws.catalogsubdomain.dataacess.author.AuthorRepository;
import org.bessam.mylocallibraryws.catalogsubdomain.dataacess.author.Nationality;
import org.bessam.mylocallibraryws.catalogsubdomain.dataacess.book.BookRepository;
import org.bessam.mylocallibraryws.catalogsubdomain.mapper.author.AuthorRequestMapper;
import org.bessam.mylocallibraryws.catalogsubdomain.mapper.author.AuthorResponseMapper;
import org.bessam.mylocallibraryws.catalogsubdomain.mapper.book.BookRequestMapper;
import org.bessam.mylocallibraryws.catalogsubdomain.mapper.book.BookResponseMapper;
import org.bessam.mylocallibraryws.catalogsubdomain.presentation.author.AuthorRequestModel;
import org.bessam.mylocallibraryws.catalogsubdomain.presentation.author.AuthorResponseModel;
import org.bessam.mylocallibraryws.catalogsubdomain.presentation.book.BookResponseModel;
import org.bessam.mylocallibraryws.common.AuthorIdentifier;
import org.bessam.mylocallibraryws.utils.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl  implements AuthorService{

    private AuthorRepository authorRepository;
    private AuthorRequestMapper authorRequestMapper;
    private AuthorResponseMapper authorResponseMapper;
    private final BookRepository bookRepository;
    private final BookResponseMapper bookResponseMapper;
    private final BookRequestMapper bookRequestMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorRequestMapper authorRequestMapper, AuthorResponseMapper authorResponseMapper, BookRepository bookRepository, BookResponseMapper bookResponseMapper, BookRequestMapper bookRequestMapper) {
        this.authorRepository = authorRepository;
        this.authorRequestMapper = authorRequestMapper;
        this.authorResponseMapper = authorResponseMapper;
        this.bookRepository = bookRepository;
        this.bookResponseMapper = bookResponseMapper;
        this.bookRequestMapper = bookRequestMapper;
    }


    @Override
    public List<AuthorResponseModel> getAuthors() {
        return authorResponseMapper.entitiesToResponseModels(authorRepository.findAll());
    }

    @Override
    public List<BookResponseModel> getBooksByAuthor(String authorId) {
        return bookResponseMapper.entityListToResponseModelList(bookRepository.findAllByAuthorIdentifier_AuthorId(authorId));
    }

    @Override
    public BookResponseModel getBookByAuthor(String authorId, String bookId) {
        return bookResponseMapper.entityToResponseModel(bookRepository.findByAuthorIdentifier_AuthorIdAndBookIdentifier_BookId(authorId, bookId));
    }

    @Override
    public AuthorResponseModel getAuthor(String authorId) {
        Author author = authorRepository.findByAuthorIdentifier_AuthorId(authorId);
        if (author == null){
            throw new NotFoundException("Unknown authorId: " + authorId);
        }
        return authorResponseMapper.entityToResponseModel(author);
    }

    @Override
    public AuthorResponseModel addAuthor(AuthorRequestModel authorRequestModel) {

        Nationality nationality = new Nationality(authorRequestModel.getCountry(), authorRequestModel.getCity(), authorRequestModel.getProvince());

        Author author = authorRequestMapper.requestModelToEntity(authorRequestModel, new AuthorIdentifier(), nationality);

        author.setAuthorIdentifier(new AuthorIdentifier());
        return authorResponseMapper.entityToResponseModel(authorRepository.save(author));
    }


    @Override
    public AuthorResponseModel updateAuthor(String authorId, AuthorRequestModel authorRequestModel) {

        Author foundAuthor = authorRepository.findByAuthorIdentifier_AuthorId(authorId);

        if (foundAuthor == null){
            throw new NotFoundException("Unknown authorId: " + authorId);
        }

        Author author = authorRequestMapper.requestModelToEntity(authorRequestModel, foundAuthor.getAuthorIdentifier(), new Nationality(authorRequestModel.getCountry(), authorRequestModel.getCity(), authorRequestModel.getProvince()));

        author.setAuthorIdentifier(foundAuthor.getAuthorIdentifier());
        author.setId(foundAuthor.getId());

        return authorResponseMapper.entityToResponseModel(authorRepository.save(author));
    }

    @Override
    public void deleteAuthor(String authorId) {
        Author author = authorRepository.findByAuthorIdentifier_AuthorId(authorId);

        if (author == null){
            throw new NotFoundException("Unknown authorId: " + authorId);
        }
        authorRepository.delete(author);
    }
}
