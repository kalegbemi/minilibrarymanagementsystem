package com.alexxycode.minilibrarymanagementsystem.repository;

import com.alexxycode.minilibrarymanagementsystem.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {
    List<Books> findBooksByTitle(String title);

    Books findBooksByISBN(String isbn);

    List<Books> findBooksByAuthor(String author);


}
