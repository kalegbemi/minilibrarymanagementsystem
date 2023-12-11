package com.alexxycode.minilibrarymanagementsystem.controller;

import com.alexxycode.minilibrarymanagementsystem.model.BorrowedBooks;
import com.alexxycode.minilibrarymanagementsystem.service.BorrowedBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BorrowedBooksController {

    private final BorrowedBooksService borrowedBooksService;

    @Autowired
    public BorrowedBooksController(BorrowedBooksService borrowedBooksService) {
        this.borrowedBooksService = borrowedBooksService;
    }

    @GetMapping("/borrowedbooks")
    public List<BorrowedBooks> getallbbooks(){
        return borrowedBooksService.getBorrowedBooksList();
    }

}
