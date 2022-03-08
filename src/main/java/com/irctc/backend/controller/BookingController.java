package com.irctc.backend.controller;

import com.irctc.backend.data.User;
import com.irctc.backend.data.Seat;
import com.irctc.backend.data.Coach;
import com.irctc.backend.data.Booking;
import com.irctc.backend.service.UserService;
import com.irctc.backend.service.SeatService;
import com.irctc.backend.service.CoachService;
import com.irctc.backend.service.BookingService;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserService userService;
    @Autowired
    private CoachService coachService;
    @Autowired
    private SeatService seatService;

    @PostMapping("/create")
    public HashMap<String, String> create(@RequestBody HashMap<String, String> request){
        HashMap<String, String> response = new HashMap<String, String>();
        try {
          User user = userService.findById(request.get("userId"));
          Coach coach = coachService.findById(request.get("coachId"));
          Seat seat = seatService.findByCoachIdAndNumber(coach.getId(), Integer.parseInt(request.get("seatNo")));
          if (seat.getStatus().equals("RESERVED")) {
            response.put("Success", "false");
            response.put("Message", "Seat already reserved");
            return response;
          }
          seat.status = "RESERVED";
          seatService.save(seat);
          Booking booking = bookingService.save(new Booking(user.getId(), seat.getId()));
          response.put("Success", "true");
          response.put("BookingId", booking.getId());
          response.put("Message", "Seat reserved successfully");
          return response;
        } catch (Exception e) {
          response.put("Success", "false");
          response.put("Error", "Invalid data");
          return response;
        }
    }

    @PostMapping("/create_multiple")
    public HashMap<String, String> createMultiple(@RequestBody HashMap<String, String> request){
        HashMap<String, String> response = new HashMap<String, String>();
        try {
          User user = userService.findById(request.get("userId"));
          Coach coach = coachService.findById(request.get("coachId"));

          int[] seatNumbers = Arrays.asList(request.get("seatNos").split(","))
          .stream()
          .map(String::trim).mapToInt(Integer::parseInt).toArray();

          // Checking iff all seats are unreserved
          for (int seatNo : seatNumbers) {
            Seat seat = seatService.findByCoachIdAndNumber(coach.getId(), seatNo);
            if (seat.getStatus().equals("RESERVED")) {
              response.put("Success", "false");
              response.put("Message", "One or more seats already reserved");
              return response;
            }
          }

          // Booking seats
          for (int seatNo : seatNumbers) {
            Seat seat = seatService.findByCoachIdAndNumber(coach.getId(), seatNo);
            seat.status = "RESERVED";
            seatService.save(seat);
            Booking booking = bookingService.save(new Booking(user.getId(), seat.getId()));
            response.put(booking.getId(), seat.getId());
          }
          response.put("Success", "true");
          response.put("Message", "Seats reserved successfully");
          return response;
        } catch (Exception e) {
          response.put("Success", "false");
          response.put("Error", "Invalid data");
          return response;
        }
    }

    @GetMapping
    public List<Booking> findAll(){
        return bookingService.findAll();
    }

}
