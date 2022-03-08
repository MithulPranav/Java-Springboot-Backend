package com.irctc.backend.service;

import com.irctc.backend.data.Coach;
import com.irctc.backend.exception.EntityNotFoundException;
import com.irctc.backend.repository.CoachRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;

    public List<Coach> findAll(){
        return coachRepository.findAll();
    }

    public Coach findById(String id){
        return coachRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Coach> findAllByTrainId(String trainId){
        return coachRepository.findAllByTrainId(trainId);
    }

    public Coach save(Coach coach){
        return coachRepository.save(coach);
    }


    public void deleteById(String id){
        coachRepository.deleteById(id);
    }
}