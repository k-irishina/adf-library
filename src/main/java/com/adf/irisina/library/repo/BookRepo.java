package com.adf.irisina.library.repo;

import com.adf.irisina.library.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends CrudRepository<Book, String> {

}