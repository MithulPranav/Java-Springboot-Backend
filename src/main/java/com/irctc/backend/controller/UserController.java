package com.irctc.backend.controller;

import com.irctc.backend.data.User;
import com.irctc.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User create(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id){
        userService.deleteById(id);
    }
}
