package com.library.org.library_system.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.org.library_system.controller.BookController;
import com.library.org.library_system.model.Book;
import com.library.org.library_system.model.Booking;
import com.library.org.library_system.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {BookController.class})
@ExtendWith(SpringExtension.class)
public class BookControllerTest {
    @Autowired
    private BookController bookController;

    @MockBean
    private BookService bookService;

    @Test
    public void testFindBookById() throws Exception {
        Book book = new Book();
        book.setListBooking(new HashSet<Booking>());
        book.setId(123L);
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setAvailability(true);
        when(this.bookService.findBookById((Long) any())).thenReturn(book);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":123,\"title\":\"Clean Code\",\"author\":\"Robert C. Martin\",\"availability\":true,\"listBooking\":[]}"));
    }

    @Test
    public void testAddBook() throws Exception {
        doNothing().when(this.bookService).addBook((Book) any());

        Book book = new Book();
        book.setListBooking(new HashSet<Booking>());
        book.setId(123L);
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setAvailability(true);
        String content = (new ObjectMapper()).writeValueAsString(book);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/books/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book book = new Book();
        book.setListBooking(new HashSet<Booking>());
        book.setId(123L);
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setAvailability(true);
        when(this.bookService.updateBook((Long) any(), (Book) any())).thenReturn(book);

        Book book1 = new Book();
        book1.setListBooking(new HashSet<Booking>());
        book1.setId(123L);
        book1.setTitle("Clean Code");
        book1.setAuthor("Robert C. Martin");
        book1.setAvailability(true);
        String content = (new ObjectMapper()).writeValueAsString(book1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/books/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":123,\"title\":\"Clean Code\",\"author\":\"Robert C. Martin\",\"availability\":true,\"listBooking\":[]}"));
    }


}

