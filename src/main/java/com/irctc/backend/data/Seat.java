package com.irctc.backend.data;

import org.springframework.data.annotation.Id;

public class Seat {

    @Id
    private String id;
    private String coachId;
    public String status;
    private int number;

    public Seat(String coachId, String status, int number){
        this.coachId = coachId;
        this.status = status;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public String coachId() {
        return coachId;
    }

    public String getStatus() {
        return status;
    }

    public int getNumber() {
        return number;
    }
}