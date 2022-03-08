package com.irctc.backend.data;

import org.springframework.data.annotation.Id;

public class Booking {

    @Id
    private String id;
    private String userId;
    private String seatId;
    // private String coachId;
    // private String trainId;
    // private String status;

    public Booking(String userId, String seatId){
        this.userId = userId;
        this.seatId = seatId;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getSeatId() {
        return seatId;
    }
}