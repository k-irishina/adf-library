package com.adf.irisina.library.model;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Domain class for book
 */
@Data
@Entity
@Table(name = "BOOKS")
public class Book implements Serializable {

    private static final long serialVersionUID = -2286256373638899976L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_ID")
    @Id
    private long bookId;

    private String bookName;
    private String isbn;
    private String year;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "READER_ID")
    private Reader currentReader;

}