package com.irctc.backend.repository;

import com.irctc.backend.data.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {}