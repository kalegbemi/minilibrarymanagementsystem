package com.alexxycode.minilibrarymanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "books",uniqueConstraints = @UniqueConstraint(name = "ISBN",columnNames = "ISBN"))
public class Books {
    @Id
    @SequenceGenerator(name = "book_generator",
            sequenceName = "book_generator",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "book_generator")
    private int id;

    @NotBlank(message = "title cannot be blank")
    @Length(max = 50, message = "title has to be atmost 50 characters long")
    private String title;

    @NotBlank(message = "please enter author can't be blank")
    @Length(min = 4, max = 26, message = "author has to be atleast 4 and atmost 26 characters long")
    private String author;

    @NotNull
    @Pattern(regexp = "[A-Z]{3}[0-9]{3}",message = "Please enter an ISBN usinge the pattern ABC123")
    private String ISBN;

    @Column(name = "publication_year")
    @Pattern(regexp = "[0-9]{4}", message = "publication year has to be 4 numbers long")
    private String publicationYear;

    public Books(String title, String author, String ISBN, String publicationYear) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id = " + id +
                "\ntitle = " + title +
                "\nauthor = " + author +
                "\nISBN= " + ISBN +
                "\npublicationYear= " + publicationYear +"\n"+
                '}'+"\n####################################\n";
    }
}
