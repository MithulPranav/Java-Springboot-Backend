package com.irctc.backend.service;

import com.irctc.backend.data.Seat;
import com.irctc.backend.exception.EntityNotFoundException;
import com.irctc.backend.repository.SeatRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> findAll(){
        return seatRepository.findAll();
    }

    public List<Seat> findAllByCoachId(String coachId){
        return seatRepository.findAllByCoachId(coachId);
    }

    public void deleteAllByCoachId(String coachId) {
        seatRepository.deleteAllByCoachId(coachId);
    }

    public Seat findByCoachIdAndNumber(String coachId, int number) {
        return seatRepository.findByCoachIdAndNumber(coachId, number);
    }

    public void save(Seat seat){
        seatRepository.save(seat);
    }

    public void deleteById(String id){
        seatRepository.deleteById(id);
    }
}