package com.irctc.backend.repository;

import java.util.*;
import com.irctc.backend.data.Coach;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoachRepository extends MongoRepository<Coach, String> {
  List<Coach> findAllByTrainId(String trainId);
}