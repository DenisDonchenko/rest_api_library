package com.library.org.library_system.controller;

import com.library.org.library_system.model.Book;
import com.library.org.library_system.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation("Get a list of book")
    @GetMapping("/all")
    public Iterable<Book> findAllBook(){
        return bookService.findAllBooks();
    }

    @ApiOperation("Get a list free books")
    @GetMapping("/free")
    public Iterable<Book> findFreeBook(){
        return bookService.findFreeBooks();
    }


    @ApiOperation("Get books by id")
    @ApiParam(name = "Book id")
    @GetMapping("/{id}")
    public Book findBookById(@PathVariable("id") Long id){
        return bookService.findBookById(id);
    }

    @ApiOperation("Adding a book")
    @PostMapping("/add")
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }

    @ApiOperation("Update book by id")
    @ApiParam(name = "Book id")
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable("id") Long id_Book,
                       @RequestBody Book book){
        return bookService.updateBook(id_Book,book);
    }
    @ApiOperation("Delete book by id")
    @ApiParam(name = "Book id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id){
        return bookService.deleteBook(id);
    }






}
