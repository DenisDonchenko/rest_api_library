package com.library.org.library_system.service;

import com.library.org.library_system.exception.ResourceNotFoundException;
import com.library.org.library_system.model.Booking;
import com.library.org.library_system.repository.BookingRepository;
import com.library.org.library_system.repository.BooksRepository;
import com.library.org.library_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private UserRepository userRepository;

    public Iterable<Booking> findAllBooking(){
        return bookingRepository.findAll();
    }

    public Booking findBookingById(Long id_booking){
        return bookingRepository.findById(id_booking).orElseThrow(()-> new ResourceNotFoundException("Booking width id"+ id_booking+ "not found"));
    }
    public Booking takeBook(Long id_book, Long id_user,String coment){
        Booking booking= new Booking();
        exceptionMethod(id_book,  id_user);
        booksRepository.updateBook(id_book,false);
        booking.setComent(coment);
        booking.setUser(userRepository.getById(id_user));
        booking.setBook(booksRepository.getById(id_book));
    return  bookingRepository.save(booking);
    }
    public void returnedBook(Long id_book){

        booksRepository.updateBook(id_book,true);
    }
    private void exceptionMethod(Long id_book, Long id_user){

        if (!booksRepository.existsById(id_book)){
            throw new ResourceNotFoundException("Book id :"+ id_book + " not found");
        }else
        if (!userRepository.existsById(id_user)){
            throw new ResourceNotFoundException("User id :"+ id_book + " not found");
        }
    }
}
