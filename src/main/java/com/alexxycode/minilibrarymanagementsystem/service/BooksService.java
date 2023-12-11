package com.alexxycode.minilibrarymanagementsystem.service;

import com.alexxycode.minilibrarymanagementsystem.exceptionHandler.NotFoundException;
import com.alexxycode.minilibrarymanagementsystem.model.Books;
import com.alexxycode.minilibrarymanagementsystem.model.BorrowedBooks;
import com.alexxycode.minilibrarymanagementsystem.model.Users;
import com.alexxycode.minilibrarymanagementsystem.repository.BooksRepository;
import com.alexxycode.minilibrarymanagementsystem.repository.BorrowedBooksRepository;
import com.alexxycode.minilibrarymanagementsystem.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    private final BooksRepository booksRepository;
    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }


    private UsersRepository usersRepository;

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    private BorrowedBooksRepository borrowedBooksRepository;
    @Autowired
    public void setBorrowedBooksRepository(BorrowedBooksRepository borrowedBooksRepository) {
        this.borrowedBooksRepository = borrowedBooksRepository;
    }

    @Cacheable("getallbooks")
    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }

    @CacheEvict(value = "getallbooks", allEntries = true)
    public String addBooks(Books books) {
        booksRepository.save(books);
        return String.format("the book (%s) with id (%d) \nhas been added to the library", books.getTitle(), books.getId());
    }

    @CacheEvict(value = "getallbooks", allEntries = true)
    public String deleteBooks(int id) {
        Books bookToBeDeleted = booksRepository.findById(id).orElse(null);
        if (booksRepository.findById(id).isPresent()) {
            booksRepository.deleteById(id);
            assert bookToBeDeleted != null;
            return String.format("the book %s with id %d \n" +
                    "has been deleted from the library", bookToBeDeleted.getTitle(), bookToBeDeleted.getId());
        }
        return ("Sorry no book with id " + id + " exists");

    }

    public Books findBookById(int id) {
        return booksRepository.findById(id).orElseThrow(()->new NotFoundException("Book with id - ("+ id + ") not  found"));
    }

    public List<Books> findBookByTitle(String title) {
        List<Books> listOfBooks = booksRepository.findBooksByTitle(title);
        if (listOfBooks.isEmpty()){
            throw new NotFoundException("Sorry we dont have any book with this title ("+title+")");
        }
        return listOfBooks;
    }

    public Books findBookByISBN(String isbn) {
        return booksRepository.findBooksByISBN(isbn);
    }

    public String findBookByAuthor(String author){
        List<Books> booksList = booksRepository.findBooksByAuthor(author);
        if (booksList.isEmpty()){
            return ("We have no book with this authors name as " + author+" in our catalog");
        }
        return booksList.toString();
    }
    @CacheEvict(value = {"getallbooks"}, allEntries = true)
    public Books updateBook(int id, Books books){

        Books bookToUpdate = booksRepository.findById(id).orElseThrow(()->new NotFoundException
                ("There is no book with this ("+id+") in our collection"));

        bookToUpdate.setAuthor(books.getAuthor());
        bookToUpdate.setISBN(books.getISBN());
        bookToUpdate.setTitle(books.getTitle());
        bookToUpdate.setPublicationYear(books.getPublicationYear());
        booksRepository.save(bookToUpdate);

        return bookToUpdate;
    }


@CacheEvict(value = "getallbooks", allEntries = true)
public String borrowBook(int userid, String isbn){
    Users users = usersRepository.findById(userid).get();
    Books bookToBorrow = booksRepository.findBooksByISBN(isbn);
    if(bookToBorrow == null){
        throw new NotFoundException("Sorry there's no book with ISBN No - " + isbn+" on our catalog");
    }

    BorrowedBooks newBorrowedBook = new BorrowedBooks();


        newBorrowedBook.setAuthor(bookToBorrow.getAuthor());
        newBorrowedBook.setTitle(bookToBorrow.getTitle());
        newBorrowedBook.setUsers(users);
        newBorrowedBook.setBookid(bookToBorrow.getId());
        borrowedBooksRepository.save(newBorrowedBook);
        booksRepository.deleteById(bookToBorrow.getId());

        return ("The user - "+ users.getFullName() +
                "\nhas borrowed the book - "+ newBorrowedBook.getTitle()) +
                "\nauthored by - " + newBorrowedBook.getAuthor();


    //return String.format("the user %s has borrowed a book '%s' and is yet to return it",users.getFullName(),bbooks.getTitle());
}

}
