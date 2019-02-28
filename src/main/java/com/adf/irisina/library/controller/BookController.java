package com.adf.irisina.library.controller;

import com.adf.irisina.library.model.Book;
import com.adf.irisina.library.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

    private static final Logger LOG = LogManager.getLogger(BookController.class);
    private static final String BOOK_DELETE_ERROR = "Could not delete book.";


    private static final String NO_BOOK_PRESENT = "No book with id %s is stored.";
    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    @ResponseBody
    public Collection<Book> getListBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Book addNewBook(@RequestBody final Book book){
        return bookService.addBook(book);
    }

    @GetMapping("/book/{bookId}")
    @ResponseBody
    public Book getBook(@PathVariable final long bookId) {
        return bookService.getBook(bookId).orElseThrow(() -> new NoSuchElementException(String.format(NO_BOOK_PRESENT, bookId)));
    }

    @PostMapping("/update")
    @ResponseBody
    public Book updateBook(@RequestBody Book book) {
        try {
            return bookService.updateBook(book);
        } catch (EntityNotFoundException ex) {
            LOG.info(ex);
            throw new NoSuchElementException(String.format(NO_BOOK_PRESENT, book.getBookId()));
        }
    }

    @PostMapping("/assign/{bookId}/reader/{readerId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Book assignBook(@PathVariable final long bookId, @PathVariable final long readerId) {
        //service to assign book to user
        return bookService.setReader(bookId, readerId);
    }

    @DeleteMapping("/delete/{bookId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteBook(@PathVariable final long bookId) {
        boolean completed;
        try {
            completed = bookService.deleteBook(bookId);
        } catch (IllegalArgumentException | EmptyResultDataAccessException ex) {
            LOG.warn(ex);
            throw new NoSuchElementException(String.format(NO_BOOK_PRESENT, bookId));
        }
        return completed ? String.format("Successfully deleted book with id %s", bookId) : BOOK_DELETE_ERROR;
    }

}
