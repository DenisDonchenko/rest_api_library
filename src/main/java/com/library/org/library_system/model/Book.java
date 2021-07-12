package com.library.org.library_system.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "availability")
    private boolean availability;


    @OneToMany(mappedBy = "book",targetEntity = Booking.class)
    private Set<Booking> listBooking;

    public Book() {    }

    public Book(Long id, String title, String author, boolean availability, Set<Booking> listBooking) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.availability = availability;
        this.listBooking = listBooking;
    }

    public Set<Booking> getListBooking() {
        return listBooking;
    }

    public void setListBooking(Set<Booking> listBooking) {
        this.listBooking = listBooking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }


}
