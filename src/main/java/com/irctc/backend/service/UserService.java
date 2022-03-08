package com.irctc.backend.service;

import com.irctc.backend.data.User;
import com.irctc.backend.exception.EntityNotFoundException;
import com.irctc.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

      public List<User> findAll(){
        return userRepository.findAll();
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public User findById(String id){
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void deleteById(String id){
        userRepository.deleteById(id);
    }
}