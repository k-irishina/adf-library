package com.adf.irisina.library.repo;

import com.adf.irisina.library.model.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepo extends CrudRepository<Reader, String> {
}
