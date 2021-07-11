package com.library.org.library_system.controller;

import com.library.org.library_system.model.Booking;
import com.library.org.library_system.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/take/{id_book}/{id_user}")
    public Booking takeBook(@PathVariable(value = "id_book") Long id_book,
                            @PathVariable(value = "id_user") Long id_user){
       return bookingService.takeBook(id_book,id_user,"Book taken");

    }//Book take
    @PostMapping("/returned/{id_book}")
    public void returnedBook(@PathVariable(value = "id_book") Long id_book){
         bookingService.returnedBook(id_book);

    }//Book returned

}
