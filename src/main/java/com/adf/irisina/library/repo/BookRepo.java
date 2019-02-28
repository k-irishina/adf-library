package com.adf.irisina.library.repo;

import com.adf.irisina.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface BookRepo extends JpaRepository<Book, Long> {
}