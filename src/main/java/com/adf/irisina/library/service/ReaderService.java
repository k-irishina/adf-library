package com.adf.irisina.library.service;

import com.adf.irisina.library.model.Book;
import com.adf.irisina.library.model.Reader;

import java.util.Collection;
import java.util.Optional;

public interface ReaderService {

    Reader addReader(Reader reader);

    Optional<Reader> getReader(long bookId);

    Reader updateReader(long bookId, Reader book);

    boolean deleteReader(long bookId);

    Collection<Book> assignedBooks(long readerId);
}
