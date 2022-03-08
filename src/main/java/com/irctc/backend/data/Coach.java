package com.irctc.backend.data;

import org.springframework.data.annotation.Id;

public class Coach {

    @Id
    private String id;
    private String trainId;
    private String type;

    public Coach(String trainId, String type){
        this.trainId = trainId;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getType() {
        return type;
    }
}