package com.irctc.backend.service;

import com.irctc.backend.data.Train;
import com.irctc.backend.exception.EntityNotFoundException;
import com.irctc.backend.repository.TrainRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;

      public List<Train> findAll(){
        return trainRepository.findAll();
    }

    public Train findById(String id){
        return trainRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Train save(Train train){
        return trainRepository.save(train);
    }

    public void deleteById(String id){
        trainRepository.deleteById(id);
    }
}