package com.library.org.library_system.service;

import com.library.org.library_system.exception.ResourceNotFoundException;
import com.library.org.library_system.model.Book;
import com.library.org.library_system.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BooksRepository booksRepository;

    public Iterable<Book> findAllBooks(){
        return booksRepository.findAll();
    }
    public Iterable<Book> findFreeBooks(){
        return booksRepository.freeBoks();
    }
    public Book findBookById(Long id_book){
        return booksRepository.findById(id_book).orElseThrow(() -> new ResourceNotFoundException("Book width id"+ id_book+ "not found"));
    }

    public void addBook(Book book){
        booksRepository.save(book);
    }

    public Book updateBook(Long id_book, Book bookRequest){
        return booksRepository.findById(id_book).map(book -> {
            book.setAuthor(bookRequest.getAuthor());
            book.setTitle(bookRequest.getTitle());
            book.setAvailability(bookRequest.getAvailability());
            return booksRepository.save(book);
        }).orElseThrow(() -> new ResourceNotFoundException("Book width id"+ id_book+ "not found"));
    }

    public ResponseEntity<?> deleteBook(Long id_book){
        return booksRepository.findById(id_book).map(book->{
            booksRepository.delete(book);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Book width id"+ id_book+ "not found"));
    }

}
