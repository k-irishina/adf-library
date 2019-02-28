package com.adf.irisina.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Domain class for reader
 */
@Data
@Entity
public class Reader implements Serializable {
    private static final long serialVersionUID = -4688119817156509768L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "READER_ID")
    @Id
    private long readerId;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @JsonIgnoreProperties("currentReader")
    @OneToMany(mappedBy="currentReader", cascade = CascadeType.ALL)
    private Collection<Book> assignedBooks = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return readerId == reader.readerId;
    }

}
