package com.adf.irisina.library.service;

import com.adf.irisina.library.model.Book;
import com.adf.irisina.library.repo.BookRepo;
import com.adf.irisina.library.repo.ReaderRepo;
import com.adf.irisina.library.service.impl.BookServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class BookServiceTest {

    private BookService service;

    @Mock
    private BookRepo bookRepo;
    @Mock
    private ReaderRepo readerRepo;

    @Before
    public void setup() {

    }

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Ignore
    @Test
    // todo add mock for jpa repository
    public void shouldNotSetReaderNoId() {
        thrown.expect(NoSuchElementException.class);
        thrown.expectMessage("No book found with id 2.");
        service.setReader(2, 3);

        Book book = new Book();
        book.setBookId(new Date().getTime());
        when(service.getBook(3)).thenReturn(Optional.of(book));

        thrown.expect(NoSuchElementException.class);
        thrown.expectMessage("No reader found with id 3.");
        service.setReader(3, 3);


    }
}