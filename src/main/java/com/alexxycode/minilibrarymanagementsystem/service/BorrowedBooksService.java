package com.alexxycode.minilibrarymanagementsystem.service;

import com.alexxycode.minilibrarymanagementsystem.model.BorrowedBooks;
import com.alexxycode.minilibrarymanagementsystem.repository.BorrowedBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowedBooksService {

    @Autowired
    private final BorrowedBooksRepository borrowedBooksRepo;

    public BorrowedBooksService(BorrowedBooksRepository borrowedBooksRepo) {
        this.borrowedBooksRepo = borrowedBooksRepo;
    }

    public List<BorrowedBooks> getBorrowedBooksList(){
        return borrowedBooksRepo.findAll();
    }
    public BorrowedBooks saveBorrowedBook(BorrowedBooks borrowedBooks){
        return borrowedBooksRepo.save(borrowedBooks);
    }

}
