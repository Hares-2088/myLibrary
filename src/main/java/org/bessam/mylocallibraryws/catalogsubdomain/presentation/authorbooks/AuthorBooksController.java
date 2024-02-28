package org.bessam.mylocallibraryws.catalogsubdomain.presentation.authorbooks;

import org.bessam.mylocallibraryws.catalogsubdomain.business.author.AuthorService;
import org.bessam.mylocallibraryws.catalogsubdomain.presentation.book.BookResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors/{authorId}/books")
public class AuthorBooksController {

   AuthorService authorService;

   @Autowired
    public AuthorBooksController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<BookResponseModel>> getBooksByAuthor(@PathVariable String authorId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(authorService.getBooksByAuthor(authorId));
    }

    @GetMapping(value = "/{bookId}", produces = "application/json")
    public ResponseEntity<BookResponseModel> getBookByAuthor(@PathVariable String authorId, @PathVariable String bookId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(authorService.getBookByAuthor(authorId, bookId));
    }
}
