package com.adf.irisina.library.service.impl;

import com.adf.irisina.library.model.Book;
import com.adf.irisina.library.model.Reader;
import com.adf.irisina.library.repo.ReaderRepo;
import com.adf.irisina.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    private ReaderRepo repository;

    @Override
    public Reader addReader(Reader reader) {
        return repository.save(reader);
    }

    @Override
    public Optional<Reader> getReader(long readerId) {
        return repository.findById(readerId);
    }

    @Override
    public Reader updateReader(long readerId, Reader reader) {
        return repository.save(reader);
    }

    @Override
    public boolean deleteReader(long readerId) {
        Optional<Reader> optReader2 = getReader(readerId);
        if (optReader2.filter(reader -> reader.getAssignedBooks().isEmpty()).isPresent()) {
        repository.deleteById(readerId);
        return true;
    } else
            return false;
}

    @Override
    public Collection<Book> assignedBooks(long readerId) {
        Optional<Reader> optReader = getReader(readerId);
        return optReader.map(Reader::getAssignedBooks).orElseThrow(IllegalArgumentException::new);
    }

}
