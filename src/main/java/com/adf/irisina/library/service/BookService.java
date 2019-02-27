package com.adf.irisina.library.service;

import com.adf.irisina.library.model.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookService {

    public Book addBook(Book book);

    public Optional<Book> getBook(String bookId);

    public Book updateBook(String bookId, Book book);

    public void deleteBook(String bookId);

    public Collection<Book> getAllBooks();

    public Book setReader(String bookId, String readerId);

}