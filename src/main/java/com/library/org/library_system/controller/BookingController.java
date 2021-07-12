package com.library.org.library_system.controller;

import com.library.org.library_system.model.Booking;
import com.library.org.library_system.service.BookingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @ApiOperation("Get a list of booking")
    @GetMapping("/all")
    public Iterable<Booking> findAllBooking(){
        return bookingService.findAllBooking();
    }

    @ApiOperation("Take book by book id")
    @PostMapping("/take/{id_book}/{id_user}")
    public Booking takeBook(@PathVariable(value = "id_book") Long id_book,
                            @PathVariable(value = "id_user") Long id_user){
       return bookingService.takeBook(id_book,id_user);

    }
    @ApiOperation("Returned book by book id")
    @PostMapping("/returned/{id_book}/{id_user}")
    public void returnedBook(@PathVariable(value = "id_book") Long id_book,
                             @PathVariable(value = "id_user") Long id_user){
         bookingService.returnedBook(id_book,id_user);

    }

}
