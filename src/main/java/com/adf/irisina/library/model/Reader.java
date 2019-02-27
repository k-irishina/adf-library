package com.adf.irisina.library.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Domain class for reader
 */
@Data
@Entity
public class Reader implements Serializable {
    private static final long serialVersionUID = -4688119817156509768L;

    @TableGenerator(name = "READER_GEN")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Id
    private String readerId;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @OneToMany(mappedBy=".currentReader")
    Collection<Book> assignedBooks = new ArrayList<>();

}
