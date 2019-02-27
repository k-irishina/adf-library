package com.adf.irisina.library.service.impl;

import com.adf.irisina.library.model.Book;
import com.adf.irisina.library.repo.BookRepo;
import com.adf.irisina.library.repo.ReaderRepo;
import com.adf.irisina.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo repository;

    @Autowired
    private ReaderRepo readerRepo;

    @Override
    public Book addBook(Book book) {
        return repository.save(book);
    }

    @Override
    public Optional<Book> getBook(String bookId) {
        return repository.findById(bookId);
    }

    @Override
    public Book updateBook(String bookId, Book book) {
        return repository.save(book);
    }

    @Override
    public void deleteBook(String bookId) {
        repository.deleteById(bookId);
    }

    @Override
    public Collection<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        repository.findAll().forEach(books::add);
        return books;
    }

    @Override
    public Book setReader(String bookId, String readerId) {
        Optional<Book> book = Optional.of(repository.findById(bookId).orElseThrow(() -> new NoSuchElementException("yup")));

        book.ifPresent(book1 -> book1.setCurrentReader(Optional.of(readerRepo.findById(readerId))
                .get().orElseThrow(() -> new NoSuchElementException("yup"))));

        return repository.save(book.get());
    }

}