package com.library.org.library_system.controller;

import com.library.org.library_system.model.User;
import com.library.org.library_system.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("Get a list of user")
    @GetMapping("/all")
    public Iterable<User> findAllUser(){ return userService.findAllUsers(); }

    @ApiOperation("Get user by id")
    @ApiParam(name = "User id")
    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") Long id){ return  userService.findUserById(id);}

    @ApiOperation("Adding a user")
    @PostMapping("/add")
    public void addUser(@RequestBody User user){ userService.addUser(user);}

    @ApiOperation("Update user by id")
    @ApiParam(name = "User id")
    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id_user, @RequestBody User user){
        return userService.updateUser(id_user,user);
    }
    @ApiOperation("Delete user by id")
    @ApiParam(name = "User id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        return userService.deleteUser(id);
    }
}
