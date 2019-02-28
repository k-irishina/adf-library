package com.adf.irisina.library.service;

import com.adf.irisina.library.model.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookService {

    Book addBook(Book book);

    Optional<Book> getBook(long bookId);

    Book updateBook(Book book);

    boolean deleteBook(long bookId);

    Collection<Book> getAllBooks();

    Book setReader(long bookId, long readerId);

}