package com.irctc.backend.repository;

import java.util.*;
import com.irctc.backend.data.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking, String> {
}