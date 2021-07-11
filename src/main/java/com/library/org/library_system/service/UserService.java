package com.library.org.library_system.service;

import com.library.org.library_system.exception.ResourceNotFoundException;
import com.library.org.library_system.model.User;
import com.library.org.library_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAllUsers(){
        return userRepository.findAll();
    }
    public User findUserById(Long id_user){
        return userRepository.findById(id_user).orElseThrow(() -> new ResourceNotFoundException("User width id"+ id_user+ "not found"));
    }
    public void addUser(User user){
        userRepository.save(user);
    }
    public User updateUser(Long id_user, User userRequest){
        return userRepository.findById(id_user).map(user -> {
            user.setFirst_name(userRequest.getFirst_name());
            user.setLast_name(userRequest.getLast_name());
            user.setPhone_number(userRequest.getPhone_number());
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("Book width id"+ id_user+ "not found"));
    }

    public ResponseEntity<?> deleteUser(Long id_user){
        return userRepository.findById(id_user).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Book width id"+ id_user+ "not found"));
    }

}
