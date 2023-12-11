Project Name: Mini Library Management System
Project Description: The Mini Library Management System is a Java-based project that helps users manage a list of books in a library. It provides basic functionalities for adding, removing, searching, and displaying books.
A user is only allowed to borrow a book ones
The book borrowed should be removed from the available book list.
Features:
Entities:
Users (email{unique}, fullName{6,15}, age{18,70}, address) @Valid
@Cacheable(getAllUsers) 
@CacheEvict(addUser, deleteUser, updateUser)
Methods(addUser, deletUser, updateUser, findUserById, findUserByFullName, findUserByEmail)
Books (title, author, isbn, publication year) @Valid
@Cacheable(getAllBooks)
@CacheEvict(addBook, borrowBook, deleteBook)
@Methods(addBook, deleteBook, borrowBook, findBookById, findBookByTitle, findBookByISBNNo)
Borrowed Books(User_id, book Id, book name, book author)

