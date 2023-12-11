package com.alexxycode.minilibrarymanagementsystem.controller;

import com.alexxycode.minilibrarymanagementsystem.model.Books;
import com.alexxycode.minilibrarymanagementsystem.service.BooksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BooksController {

    private final BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/books")
    public List<Books> getAllBooks() {
        return booksService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Books findBookById(@PathVariable int id){
        return booksService.findBookById(id);
    }

    @GetMapping("/books/title/{title}")
    public List<Books> findBookByTitle(@PathVariable String title){
        return booksService.findBookByTitle(title);
    }

    @GetMapping("/books/isbn/{isbn}")
    public Books findBookByISBNNo(@PathVariable String isbn){
        return booksService.findBookByISBN(isbn);
    }

    @PostMapping("/add/books")
    public String addBook(@Valid @RequestBody Books books){
        return booksService.addBooks(books);
    }

    @DeleteMapping("/delete/books/{id}")
    public String deleteBook(@PathVariable int id){
        return booksService.deleteBooks(id);
    }

    @GetMapping("/books/author/{author}")
    public String findBookByAuthor(@PathVariable String author){
        return booksService.findBookByAuthor(author);
    }

    @PutMapping("/books/update/{id}")
    public Books updateBook(@Valid @PathVariable int id, @RequestBody Books books){
        return booksService.updateBook(id, books);
    }

    @DeleteMapping("/borrowbook/{id}/{isbn}")
    public String borrowBook(@PathVariable int id,@PathVariable String isbn){
        return booksService.borrowBook(id,isbn);
    }

}
