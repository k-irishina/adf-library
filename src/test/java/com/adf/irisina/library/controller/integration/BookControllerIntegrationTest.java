package com.adf.irisina.library.controller.integration;

import com.adf.irisina.library.application.AdfApplication;
import com.adf.irisina.library.model.Book;
import com.adf.irisina.library.model.Reader;
import com.adf.irisina.library.service.BookService;
import com.adf.irisina.library.service.ReaderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdfApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookService service;

    @Autowired
    private ReaderService readerService;

    /**
     * Test for storing a new book.
     */
    @Test
    public void shouldStoreBook() {
        Book book = generateBook();

        ResponseEntity<Book> responseEntity = restTemplate.postForEntity("/books/add", book, Book.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Book stored = responseEntity.getBody();

        assertNotNull(stored);
        assertNotEquals(book.getBookId(), stored.getBookId());

        Optional<Book> optFromService = service.getBook(stored.getBookId());
        assertTrue(optFromService.isPresent());
        assertEquals(stored, optFromService.get());
    }

    @Test
    public void shouldUpdateBook() {
        final Book book = generateBook();
        final Book stored = service.addBook(book);

        book.setYear("2018");
        book.setBookName("Book name");
        book.setBookId(stored.getBookId());

        ResponseEntity<Book> responseEntity = restTemplate.postForEntity("/books/update", book, Book.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Optional<Book> optFromService = service.getBook(stored.getBookId());
        assertTrue(optFromService.isPresent());
        assertEquals(book, optFromService.get());

        final Book retrieved = responseEntity.getBody();
        assertEquals(book, retrieved);
    }

    @Test
    public void shouldNotDeleteStoredBook() {
        final Book book = generateBook();
        final Book stored = service.addBook(book);

        final String endpoint = "/books/delete/" + stored.getBookId();
        ResponseEntity<String> responseEntity = restTemplate
                .exchange(endpoint, HttpMethod.DELETE, new HttpEntity<>(null), String.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Could not delete book.", responseEntity.getBody());
    }

    @Test
    public void shouldRetrieveBook() {
        final Book stored = service.addBook(generateBook());

        final String endpoint = "/books/book/" + stored.getBookId();
        ResponseEntity<Book> responseEntity = restTemplate.getForEntity(endpoint, Book.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(stored, responseEntity.getBody());
    }

    @Test
    public void shouldSetReader() {
        final Book stored = service.addBook(generateBook());

        final Reader reader = new Reader();
        reader.setFirstName("Name");
        reader.setLastName("LastName");
        final Reader storedReader = readerService.addReader(reader);

        final String endpoint = String.format("/books/assign/%s/reader/%s", stored.getBookId(), reader.getReaderId());

        ResponseEntity<Book> responseEntity = restTemplate.postForEntity(endpoint, null, Book.class);

        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        Book updated = responseEntity.getBody();

        assertNotNull(updated);

        assertEquals(storedReader, updated.getCurrentReader());
    }

    // Helper method to generate book entity
    private Book generateBook() {
        Book book = new Book();
        book.setBookId(34);
        book.setBookName("Crime and punishment");
        book.setIsbn("2834524");
        book.setYear("2008");
        return book;
    }

}
