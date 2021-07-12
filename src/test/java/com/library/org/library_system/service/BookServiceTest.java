package com.library.org.library_system.service;

import com.library.org.library_system.exception.ResourceNotFoundException;
import com.library.org.library_system.model.Book;
import com.library.org.library_system.model.Booking;
import com.library.org.library_system.repository.BooksRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {BookService.class})
@ExtendWith(SpringExtension.class)
public class BookServiceTest {
    @Autowired
    private BookService bookService;

    @MockBean
    private BooksRepository booksRepository;

    @Test
    public void testFindAllBooks() {
        ArrayList<Book> bookList = new ArrayList<Book>();
        when(this.booksRepository.findAll()).thenReturn(bookList);
        Iterable<Book> actualFindAllBooksResult = this.bookService.findAllBooks();
        assertSame(bookList, actualFindAllBooksResult);
        assertTrue(((Collection<Book>) actualFindAllBooksResult).isEmpty());
        verify(this.booksRepository).findAll();
    }

    @Test
    public void testFindFreeBooks() {
        when(this.booksRepository.freeBoks()).thenReturn((Iterable<Book>) mock(Iterable.class));
        this.bookService.findFreeBooks();
        verify(this.booksRepository).freeBoks();
        assertTrue(((Collection<Book>) this.bookService.findAllBooks()).isEmpty());
    }

    @Test
    public void testFindBookById() {
        Book book = new Book();
        book.setListBooking(new HashSet<Booking>());
        book.setId(123L);
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setAvailability(true);
        Optional<Book> ofResult = Optional.<Book>of(book);
        when(this.booksRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(book, this.bookService.findBookById(1L));
        verify(this.booksRepository).findById((Long) any());
        assertTrue(((Collection<Book>) this.bookService.findAllBooks()).isEmpty());
    }

    @Test
    public void testAddBook() {
        Book book = new Book();
        book.setListBooking(new HashSet<Booking>());
        book.setId(123L);
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setAvailability(true);
        when(this.booksRepository.save((Book) any())).thenReturn(book);

        Book book1 = new Book();
        book1.setListBooking(new HashSet<Booking>());
        book1.setId(123L);
        book1.setTitle("Clean Code");
        book1.setAuthor("Robert C. Martin");
        book1.setAvailability(true);
        this.bookService.addBook(book1);
        verify(this.booksRepository).save((Book) any());
        assertTrue(((Collection<Book>) this.bookService.findAllBooks()).isEmpty());
    }

    @Test
    public void testUpdateBook() {
        Book book = new Book();
        book.setListBooking(new HashSet<Booking>());
        book.setId(123L);
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setAvailability(true);
        Optional<Book> ofResult = Optional.<Book>of(book);

        Book book1 = new Book();
        book1.setListBooking(new HashSet<Booking>());
        book1.setId(123L);
        book1.setTitle("Clean Code");
        book1.setAuthor("Robert C. Martin");
        book1.setAvailability(true);
        when(this.booksRepository.save((Book) any())).thenReturn(book1);
        when(this.booksRepository.findById((Long) any())).thenReturn(ofResult);

        Book book2 = new Book();
        book2.setListBooking(new HashSet<Booking>());
        book2.setId(123L);
        book2.setTitle("Clean Code");
        book2.setAuthor("Robert C. Martin");
        book2.setAvailability(true);
        assertSame(book1, this.bookService.updateBook(1L, book2));
        verify(this.booksRepository).findById((Long) any());
        verify(this.booksRepository).save((Book) any());
        assertTrue(((Collection<Book>) this.bookService.findAllBooks()).isEmpty());
    }


    @Test
    public void testDeleteBook() {
        Book book = new Book();
        book.setListBooking(new HashSet<Booking>());
        book.setId(123L);
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setAvailability(true);
        Optional<Book> ofResult = Optional.<Book>of(book);
        doNothing().when(this.booksRepository).delete((Book) any());
        when(this.booksRepository.findById((Long) any())).thenReturn(ofResult);
        ResponseEntity<?> actualDeleteBookResult = this.bookService.deleteBook(1L);
        assertNull(actualDeleteBookResult.getBody());
        assertEquals("<200 OK OK,[]>", actualDeleteBookResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteBookResult.getStatusCode());
        assertTrue(actualDeleteBookResult.getHeaders().isEmpty());
        verify(this.booksRepository).delete((Book) any());
        verify(this.booksRepository).findById((Long) any());
        assertTrue(((Collection<Book>) this.bookService.findAllBooks()).isEmpty());
    }

}

