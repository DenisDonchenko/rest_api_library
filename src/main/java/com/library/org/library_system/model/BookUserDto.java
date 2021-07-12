package com.library.org.library_system.model;

import javax.persistence.Column;

public class BookUserDto {
    private Long id_book;
    private String title;
    private String author;
    private Long id_user;
    private String first_name;
    private String last_name;
    private String phone_number;

    public BookUserDto(Long id_book, String title, String author, Long id_user, String first_name, String last_name, String phone_number) {
        this.id_book = id_book;
        this.title = title;
        this.author = author;
        this.id_user = id_user;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "BookUserDto{" +
                "id_user=" + id_user +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", id_book=" + id_book +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public Long getId_book() {
        return id_book;
    }

    public void setId_book(Long id_book) {
        this.id_book = id_book;
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

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
