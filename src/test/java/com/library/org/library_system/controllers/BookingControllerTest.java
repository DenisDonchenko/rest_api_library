package com.library.org.library_system.controllers;

import com.library.org.library_system.controller.BookingController;
import com.library.org.library_system.model.Book;
import com.library.org.library_system.model.Booking;
import com.library.org.library_system.model.User;
import com.library.org.library_system.service.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {BookingController.class})
@ExtendWith(SpringExtension.class)
public class BookingControllerTest {
    @Autowired
    private BookingController bookingController;

    @MockBean
    private BookingService bookingService;

    @Test
    public void testTakeBook() throws Exception {
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
        booking.setComent("Book taken");
        booking.setId(123L);
        booking.setUser(user);
        booking.setBook(book);
        when(this.bookingService.takeBook((Long) any(), (Long) any())).thenReturn(booking);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/booking/take/{id_book}/{id_user}", 1L,
                1L);
        MockMvcBuilders.standaloneSetup(this.bookingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":123,\"coment\":\"Book taken\"}"));
    }

    @Test
    public void testReturnedBook() throws Exception {
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
        booking.setComent("Book returned");
        booking.setId(123L);
        booking.setUser(user);
        booking.setBook(book);
        when(this.bookingService.returnedBook((Long) any(), (Long) any())).thenReturn(booking);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/booking/returned/{id_book}/{id_user}",
                1L, 1L);
        MockMvcBuilders.standaloneSetup(this.bookingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

