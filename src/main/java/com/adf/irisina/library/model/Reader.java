package com.adf.irisina.library.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Domain class for reader
 */
@Data
@Entity
@Table(name = "READERS")
public class Reader implements Serializable {
    private static final long serialVersionUID = -4688119817156509768L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "READER_ID")
    @Id
    private long readerId;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @OneToMany(mappedBy="currentReader", cascade = CascadeType.ALL)
    private Collection<Book> assignedBooks = new ArrayList<>();

    public void addBook(Book book) {
        if (!getAssignedBooks().contains(book)) {
            getAssignedBooks().add(book);
            if (book.getCurrentReader() != null) {
                book.getCurrentReader().getAssignedBooks().remove(book);
            }
            book.setCurrentReader(this);
        }
    }

}
