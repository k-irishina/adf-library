package com.adf.irisina.library.controller;

import com.adf.irisina.library.model.Reader;
import com.adf.irisina.library.service.ReaderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/readers")
@CrossOrigin(origins = "http://localhost:4200")
public class ReaderController {

    private static final Logger LOG = LogManager.getLogger(ReaderController.class);

    private static final String READER_OWNS_BOOKS_ERROR = "Cannot delete reader, as this reader has books assigned.";

    @Autowired
    private ReaderService service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Reader registerReader(@RequestBody final Reader reader){
        return service.addReader(reader);
    }

    @GetMapping("/reader/{readerId}")
    @ResponseBody
    public Reader getReader(@PathVariable final long readerId) {
        return service.getReader(readerId).orElseThrow(() -> new NoSuchElementException(String.format("No reader with id %s was found", readerId)));
    }

    @PostMapping("/update/{readerId]")
    @ResponseBody
    public Reader updateReader(@PathVariable final long readerId, @RequestBody Reader reader) {
        return service.updateReader(readerId, reader);
    }

    @DeleteMapping("/delete/{readerId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteReader(@PathVariable final long readerId) {
        boolean completed;
        try {
            completed = service.deleteReader(readerId);
        } catch (IllegalArgumentException | EmptyResultDataAccessException ex) {
            LOG.warn(ex);
            throw new NoSuchElementException(String.format("No reader found with id %s!", readerId));
        }
        return completed ?
                String.format("Successfully deleted reader with id %s", readerId) : READER_OWNS_BOOKS_ERROR;
    }

}
