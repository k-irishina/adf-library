package com.adf.irisina.library.service.impl;

import com.adf.irisina.library.model.Book;
import com.adf.irisina.library.model.Reader;
import com.adf.irisina.library.repo.BookRepo;
import com.adf.irisina.library.repo.ReaderRepo;
import com.adf.irisina.library.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger LOG = LogManager.getLogger(BookService.class);

    @Autowired
    private BookRepo repository;

    @Autowired
    private ReaderRepo readerRepo;

    @Override
    public Book addBook(Book book) {
        return repository.save(book);
    }

    @Override
    public Optional<Book> getBook(long bookId) {
        return repository.findById(bookId);
    }

    @Override
    public Book updateBook(Book book) {
        return repository.save(book);
    }

    @Override
    public boolean deleteBook(long bookId) {
        Optional<Book> optBook = getBook(bookId);
        if (optBook.filter(book -> book.getCurrentReader() == null).isPresent()) {
            repository.deleteById(bookId);
            return true;
        } else
            return false;
    }

    @Override
    public Collection<Book> getAllBooks() {
        List<Book> books = new ArrayList<>(repository.findAll());
        if (books.isEmpty()) {
            LOG.info("No books found in storage.");
        }
        return books;
    }

    @Override
    public Book setReader(long bookId, long readerId) {
        Optional<Book> book = Optional.of(repository.findById(bookId).orElseThrow(() -> new NoSuchElementException(String.format("No book found with id %s.", bookId))));
        Optional<Reader> reader = Optional.of(readerRepo.findById(readerId).orElseThrow(() -> new NoSuchElementException(String.format("No reader found with id %s.", readerId))));
        Book toStore = book.get();
        toStore.setCurrentReader(reader.get());

        return repository.save(toStore);
    }

}