package com.library.org.library_system.service;

import com.library.org.library_system.exception.ResourceNotFoundException;
import com.library.org.library_system.model.Book;
import com.library.org.library_system.model.Booking;
import com.library.org.library_system.model.User;
import com.library.org.library_system.repository.BookingRepository;
import com.library.org.library_system.repository.BooksRepository;
import com.library.org.library_system.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {BookingService.class})
@ExtendWith(SpringExtension.class)
public class BookingServiceTest {
    @MockBean
    private BookingRepository bookingRepository;

    @Autowired
    private BookingService bookingService;

    @MockBean
    private BooksRepository booksRepository;

    @MockBean
    private UserRepository userRepository;


    @Test
    public void testFindAllBooking() {
        User user = new User();
        user.setPhone_number("0967829340");
        user.setListBooking(new HashSet<Booking>());
        user.setId(123L);
        user.setLast_name("Ivanov");
        user.setFirst_name("Ivan");

        Book book = new Book();
        book.setListBooking(new HashSet<Booking>());
        book.setId(123L);
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setAvailability(true);

        Booking booking = new Booking();
        booking.setComent("Coment");
        booking.setId(123L);
        booking.setUser(user);
        booking.setBook(book);

        ArrayList<Booking> bookingList = new ArrayList<Booking>();
        bookingList.add(booking);
        when(this.bookingRepository.findAll()).thenReturn(bookingList);
        Iterable<Booking> actualFindAllBookingResult = this.bookingService.findAllBooking();
        assertSame(bookingList, actualFindAllBookingResult);
        assertEquals(1, ((Collection<Booking>) actualFindAllBookingResult).size());
        verify(this.bookingRepository, times(2)).findAll();
    }

    @Test
    public void testFindBookingById() {
        User user = new User();
        user.setPhone_number("0967829340");
        user.setListBooking(new HashSet<Booking>());
        user.setId(123L);
        user.setLast_name("Ivanov");
        user.setFirst_name("Ivan");

        Book book = new Book();
        book.setListBooking(new HashSet<Booking>());
        book.setId(123L);
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setAvailability(true);

        Booking booking = new Booking();
        booking.setComent("Coment");
        booking.setId(123L);
        booking.setUser(user);
        booking.setBook(book);
        Optional<Booking> ofResult = Optional.<Booking>of(booking);
        when(this.bookingRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(booking, this.bookingService.findBookingById(1L));
        verify(this.bookingRepository).findById((Long) any());
        assertTrue(((Collection<Booking>) this.bookingService.findAllBooking()).isEmpty());
    }

    @Test
    public void testTakeBook() {
        User user = new User();
        user.setPhone_number("0967829340");
        user.setListBooking(new HashSet<Booking>());
        user.setId(123L);
        user.setLast_name("Ivanov");
        user.setFirst_name("Ivan");
        when(this.userRepository.getById((Long) any())).thenReturn(user);
        when(this.userRepository.existsById((Long) any())).thenReturn(true);

        Book book = new Book();
        book.setListBooking(new HashSet<Booking>());
        book.setId(123L);
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setAvailability(true);
        when(this.booksRepository.getById((Long) any())).thenReturn(book);
        doNothing().when(this.booksRepository).updateBook((Long) any(), anyBoolean());
        when(this.booksRepository.existsById((Long) any())).thenReturn(true);

        User user1 = new User();
        user1.setPhone_number("0967829340");
        user1.setListBooking(new HashSet<Booking>());
        user1.setId(123L);
        user1.setLast_name("Ivanov");
        user1.setFirst_name("Ivan");

        Book book1 = new Book();
        book1.setListBooking(new HashSet<Booking>());
        book1.setId(123L);
        book1.setTitle("Clean Code");
        book1.setAuthor("Robert C. Martin");
        book1.setAvailability(true);

        Booking booking = new Booking();
        booking.setComent("Coment");
        booking.setId(123L);
        booking.setUser(user1);
        booking.setBook(book1);
        when(this.bookingRepository.save((Booking) any())).thenReturn(booking);
        assertSame(booking, this.bookingService.takeBook(1L, 1L));
        verify(this.userRepository).existsById((Long) any());
        verify(this.userRepository).getById((Long) any());
        verify(this.booksRepository).existsById((Long) any());
        verify(this.booksRepository).getById((Long) any());
        verify(this.booksRepository).updateBook((Long) any(), anyBoolean());
        verify(this.bookingRepository).save((Booking) any());
        assertTrue(((Collection<Booking>) this.bookingService.findAllBooking()).isEmpty());
    }


    @Test
    public void testReturnedBook() {
        User user = new User();
        user.setPhone_number("0967829340");
        user.setListBooking(new HashSet<Booking>());
        user.setId(123L);
        user.setLast_name("Ivanov");
        user.setFirst_name("Ivan");
        when(this.userRepository.getById((Long) any())).thenReturn(user);
        when(this.userRepository.existsById((Long) any())).thenReturn(true);

        Book book = new Book();
        book.setListBooking(new HashSet<Booking>());
        book.setId(123L);
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setAvailability(true);
        when(this.booksRepository.getById((Long) any())).thenReturn(book);
        doNothing().when(this.booksRepository).updateBook((Long) any(), anyBoolean());
        when(this.booksRepository.existsById((Long) any())).thenReturn(true);

        User user1 = new User();
        user1.setPhone_number("0967829340");
        user1.setListBooking(new HashSet<Booking>());
        user1.setId(123L);
        user1.setLast_name("Ivanov");
        user1.setFirst_name("Ivan");


        Book book1 = new Book();
        book.setListBooking(new HashSet<Booking>());
        book.setId(123L);
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setAvailability(true);

        Booking booking = new Booking();
        booking.setComent("Coment");
        booking.setId(123L);
        booking.setUser(user1);
        booking.setBook(book1);
        when(this.bookingRepository.save((Booking) any())).thenReturn(booking);
        assertSame(booking, this.bookingService.returnedBook(1L, 1L));
        verify(this.userRepository).existsById((Long) any());
        verify(this.userRepository).getById((Long) any());
        verify(this.booksRepository).existsById((Long) any());
        verify(this.booksRepository).getById((Long) any());
        verify(this.booksRepository).updateBook((Long) any(), anyBoolean());
        verify(this.bookingRepository).save((Booking) any());
        assertTrue(((Collection<Booking>) this.bookingService.findAllBooking()).isEmpty());
    }




}

