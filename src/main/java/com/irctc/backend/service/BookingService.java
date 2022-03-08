package com.irctc.backend.service;

import com.irctc.backend.data.Booking;
import com.irctc.backend.exception.EntityNotFoundException;
import com.irctc.backend.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

      public List<Booking> findAll(){
        return bookingRepository.findAll();
    }

    public Booking save(Booking booking){
        return bookingRepository.save(booking);
    }

    public Booking findById(String id){
        return bookingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}