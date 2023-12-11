package com.alexxycode.minilibrarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = ("bookid")))
public class BorrowedBooks {

    @Id
    @SequenceGenerator(name = "borrow_sequence",
            sequenceName = "borrow_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "borrow_sequence")
    private int id;
    @OneToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = ("user_id"))
    Users users;

    @Column(name = ("book_id"))
    private int bookid;

    private String title;

    private String author;


}
