package com.adf.irisina.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Domain class for book
 */
@Data
@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = -2286256373638899976L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID")
    @Id
    private long bookId;

    private String bookName;
    private String isbn;
    private String year;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "READER_ID")
    @JsonIgnoreProperties("assignedBooks")
    private Reader currentReader;

}