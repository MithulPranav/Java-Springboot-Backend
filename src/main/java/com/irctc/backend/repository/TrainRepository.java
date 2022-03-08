package com.irctc.backend.repository;

import com.irctc.backend.data.Train;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrainRepository extends MongoRepository<Train, String> {}