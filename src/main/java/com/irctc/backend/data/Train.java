package com.irctc.backend.data;

import org.springframework.data.annotation.Id;

public class Train {

    @Id
    private String id;
    private String name;

    public Train(String name){
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}