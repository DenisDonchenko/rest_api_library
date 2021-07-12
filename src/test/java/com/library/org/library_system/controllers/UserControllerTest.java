package com.library.org.library_system.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.org.library_system.controller.UserController;
import com.library.org.library_system.model.Booking;
import com.library.org.library_system.model.User;
import com.library.org.library_system.service.UserService;
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

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
public class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    public void testFindUserById() throws Exception {
        User user = new User();
        user.setPhone_number("0967829340");
        user.setListBooking(new HashSet<Booking>());
        user.setId(123L);
        user.setLast_name("Ivanov");
        user.setFirst_name("Ivan");
        when(this.userService.findUserById((Long) any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"first_name\":\"Ivan\",\"last_name\":\"Ivanov\",\"phone_number\":\"0967829340\",\"listBooking\":[]}"));
    }

    @Test
    public void testAddUser() throws Exception {
        doNothing().when(this.userService).addUser((User) any());

        User user = new User();
        user.setPhone_number("0967829340");
        user.setListBooking(new HashSet<Booking>());
        user.setId(123L);
        user.setLast_name("Ivanov");
        user.setFirst_name("Ivan");
        String content = (new ObjectMapper()).writeValueAsString(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setPhone_number("0967829340");
        user.setListBooking(new HashSet<Booking>());
        user.setId(123L);
        user.setLast_name("Ivanov");
        user.setFirst_name("Ivan");
        when(this.userService.updateUser((Long) any(), (User) any())).thenReturn(user);

        User user1 = new User();
        user1.setPhone_number("0967829340");
        user1.setListBooking(new HashSet<Booking>());
        user1.setId(123L);
        user1.setLast_name("Ivanov");
        user1.setFirst_name("Ivan");
        String content = (new ObjectMapper()).writeValueAsString(user1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"first_name\":\"Ivan\",\"last_name\":\"Ivanov\",\"phone_number\":\"0967829340\",\"listBooking\":[]}"));
    }

}

