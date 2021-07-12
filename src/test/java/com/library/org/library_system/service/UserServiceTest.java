package com.library.org.library_system.service;

import com.library.org.library_system.exception.ResourceNotFoundException;
import com.library.org.library_system.model.Booking;
import com.library.org.library_system.model.User;
import com.library.org.library_system.repository.UserRepository;
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

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testFindAllUsers() {
        ArrayList<User> userList = new ArrayList<User>();
        when(this.userRepository.findAll()).thenReturn(userList);
        Iterable<User> actualFindAllUsersResult = this.userService.findAllUsers();
        assertSame(userList, actualFindAllUsersResult);
        assertTrue(((Collection<User>) actualFindAllUsersResult).isEmpty());
        verify(this.userRepository).findAll();
    }

    @Test
    public void testFindUserById() {
        User user = new User();
        user.setPhone_number("0967829340");
        user.setListBooking(new HashSet<Booking>());
        user.setId(123L);
        user.setLast_name("Ivanov");
        user.setFirst_name("Ivan");
        Optional<User> ofResult = Optional.<User>of(user);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(user, this.userService.findUserById(1L));
        verify(this.userRepository).findById((Long) any());
        assertTrue(((Collection<User>) this.userService.findAllUsers()).isEmpty());
    }


    @Test
    public void testAddUser() {
        User user = new User();
        user.setPhone_number("0967829340");
        user.setListBooking(new HashSet<Booking>());
        user.setId(123L);
        user.setLast_name("Ivanov");
        user.setFirst_name("Ivan");
        when(this.userRepository.save((User) any())).thenReturn(user);

        User user1 = new User();
        user.setPhone_number("0967829340");
        user.setListBooking(new HashSet<Booking>());
        user.setId(123L);
        user.setLast_name("Ivanov");
        user.setFirst_name("Ivan");
        this.userService.addUser(user1);
        verify(this.userRepository).save((User) any());
        assertTrue(((Collection<User>) this.userService.findAllUsers()).isEmpty());
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setPhone_number("0967829340");
        user.setListBooking(new HashSet<Booking>());
        user.setId(123L);
        user.setLast_name("Ivanov");
        user.setFirst_name("Ivan");
        Optional<User> ofResult = Optional.<User>of(user);

        User user1 = new User();
        user1.setPhone_number("0967829340");
        user1.setListBooking(new HashSet<Booking>());
        user1.setId(123L);
        user1.setLast_name("Ivanov");
        user1.setFirst_name("Ivan");
        when(this.userRepository.save((User) any())).thenReturn(user1);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);

        User user2 = new User();
        user2.setPhone_number("0967829340");
        user2.setListBooking(new HashSet<Booking>());
        user2.setId(123L);
        user2.setLast_name("Ivanov");
        user2.setFirst_name("Ivan");
        assertSame(user1, this.userService.updateUser(1L, user2));
        verify(this.userRepository).findById((Long) any());
        verify(this.userRepository).save((User) any());
        assertTrue(((Collection<User>) this.userService.findAllUsers()).isEmpty());
    }


    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setPhone_number("0967829340");
        user.setListBooking(new HashSet<Booking>());
        user.setId(123L);
        user.setLast_name("Ivanov");
        user.setFirst_name("Ivan");
        Optional<User> ofResult = Optional.<User>of(user);
        doNothing().when(this.userRepository).delete((User) any());
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        ResponseEntity<?> actualDeleteUserResult = this.userService.deleteUser(1L);
        assertNull(actualDeleteUserResult.getBody());
        assertEquals("<200 OK OK,[]>", actualDeleteUserResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteUserResult.getStatusCode());
        assertTrue(actualDeleteUserResult.getHeaders().isEmpty());
        verify(this.userRepository).delete((User) any());
        verify(this.userRepository).findById((Long) any());
        assertTrue(((Collection<User>) this.userService.findAllUsers()).isEmpty());
    }

}

