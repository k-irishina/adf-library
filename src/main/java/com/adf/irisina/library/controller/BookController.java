package com.adf.irisina.library.controller;

import com.adf.irisina.library.model.Book;
import com.adf.irisina.library.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/books")
public class BookController {

    public static final Logger LOG = LogManager.getLogger(BookController.class);

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
        return bookService.getBook(bookId).orElseThrow(() -> new NoSuchElementException(String.format("No book with bookId %s was found", bookId)));
    }

    @PostMapping("/update/{bookId]")
    @ResponseBody
    public Book updateBook(@PathVariable final long bookId, @RequestBody Book book) {
        return bookService.updateBook(bookId, book);
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
        try {
            bookService.deleteBook(bookId);
        } catch (IllegalArgumentException ex) {
            LOG.warn("");
            throw new NoSuchElementException(String.format("No book found with id %s", bookId));
        } catch (EmptyResultDataAccessException ex ) {
            LOG.warn(ex);
            throw new NoSuchElementException(String.format("No book found with id %s", bookId));
        }
        return String.format("Successfully deleted book with id %s", bookId);
    }

}
