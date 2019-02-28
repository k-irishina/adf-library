package com.adf.irisina.library.controller.integration;

import com.adf.irisina.library.application.AdfApplication;
import com.adf.irisina.library.model.Book;
import com.adf.irisina.library.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
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

    /**
     * Test for storing a new book.
     */
    @Test
    public void shouldStoreBook() {
        Book book = new Book();
        book.setBookId(34);
        book.setBookName("Crime and punishment");
        book.setIsbn("2834524");
        book.setYear("2008");

        ResponseEntity<Book> responseEntity = restTemplate.postForEntity("/books/add", book, Book.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Book stored = responseEntity.getBody();

        assertNotNull(stored);
        assertNotEquals(book.getBookId(), stored.getBookId());

        Optional<Book> optFromService = service.getBook(stored.getBookId());
        assertTrue(optFromService.isPresent());
        assertEquals(stored, optFromService.get());

    }
}
