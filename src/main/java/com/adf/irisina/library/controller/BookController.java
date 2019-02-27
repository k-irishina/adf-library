package com.adf.irisina.library.controller;

import com.adf.irisina.library.model.Book;
import com.adf.irisina.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    //public static final Logger logger = LoggerFactory.getLogger(BookRestController.class); // todo add normal logger

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    @ResponseBody
    public Collection<Book> getListBooks() {
        return Optional.ofNullable(bookService.getAllBooks()).orElseThrow(() -> new IllegalArgumentException("MSg"));
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Book addNewBook(@RequestBody final Book book){
        return bookService.addBook(book);
    }

    @GetMapping("/book/{bookId}")
    @ResponseBody
    public Book getBook(@PathVariable final String bookId) {
        return bookService.getBook(bookId).orElseThrow(() -> new NoSuchElementException(String.format("No book with bookId %s was found", bookId)));
    }

    @PostMapping("/assign")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Book assignBook(@RequestBody final String book, String userId) {
        //service to assign book to user
        return bookService.setReader(book, userId);
    }

}
