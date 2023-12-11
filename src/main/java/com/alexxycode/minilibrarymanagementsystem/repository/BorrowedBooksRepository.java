package com.alexxycode.minilibrarymanagementsystem.repository;

import com.alexxycode.minilibrarymanagementsystem.model.BorrowedBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooks, Integer> {

    //BorrowedBooks findByUsers(int userId);
}
