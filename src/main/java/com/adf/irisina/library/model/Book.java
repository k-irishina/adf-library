package com.adf.irisina.library.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Domain class for book
 */
@Data
@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = -2286256373638899976L;

    @TableGenerator(name = "BOOKS")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Id
    private String bookId;

    private String book_name;
    private String ISBN;
    private String year;
    private Boolean status;

    @ManyToOne
    private Reader currentReader;

}