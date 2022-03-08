package com.irctc.backend.controller;

import com.irctc.backend.data.Seat;
import com.irctc.backend.service.SeatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping
    public List<Seat> findAll(){
        return seatService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id){
        seatService.deleteById(id);
    }
}
