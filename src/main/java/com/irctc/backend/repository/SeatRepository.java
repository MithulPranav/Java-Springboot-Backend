package com.irctc.backend.repository;

import java.util.*;
import com.irctc.backend.data.Seat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SeatRepository extends MongoRepository<Seat, String> {
  List<Seat> findAllByCoachId(String coachId);
  void deleteAllByCoachId(String coachId);
  Seat findByCoachIdAndNumber(String coachId, int number);
}