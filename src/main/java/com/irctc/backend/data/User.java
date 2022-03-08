package com.irctc.backend.data;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    private String id;
    private String name;
    private String role;

    public User(String name, String role){
        this.name = name;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}