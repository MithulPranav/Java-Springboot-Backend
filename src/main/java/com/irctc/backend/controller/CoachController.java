package com.irctc.backend.controller;

import com.irctc.backend.data.Coach;
import com.irctc.backend.data.User;
import com.irctc.backend.data.Train;
import com.irctc.backend.data.Seat;
import com.irctc.backend.service.CoachService;
import com.irctc.backend.service.UserService;
import com.irctc.backend.service.SeatService;
import com.irctc.backend.service.TrainService;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coach")
public class CoachController {

    @Autowired
    private CoachService coachService;
    @Autowired
    private UserService userService;
    @Autowired
    private TrainService trainService;
    @Autowired
    private SeatService seatService;

    @PostMapping("/create")
    public HashMap<String, String> create(@RequestBody HashMap<String, String> request){
      User user;
      HashMap<String, String> response = new HashMap<String, String>();
      try {
        user = userService.findById(request.get("userId"));
      } catch (Exception e) {
        response.put("success", "false");
        response.put("Error", "Either User does not exist or Data invalid");
        return response;
      }
      if (!user.getRole().equals("Admin")) {
        response.put("success", "false");
        response.put("Error", "Access Denied");
        return response;
      }
      try {
        Train train = trainService.findById(request.get("trainId"));
        String coachType = request.get("coachType");
        // A/C Sleeper -> 0
        // Non A/C Sleeper -> 1
        // Seater -> 2
        if (coachType.equals("0") || coachType.equals("1") || coachType.equals("2")) {
          // Coach Creation
          Coach coach = coachService.save(new Coach(request.get("trainId"), request.get("coachType")));

          int numberOfSeats = 60;
          if (coach.getType().equals("2")) {
            numberOfSeats = 120;
          }
          for (int i = 0; i < numberOfSeats; i++) {
            seatService.save(new Seat(coach.getId(), "UN_RESERVED", i + 1));
          }

          response.put("id", coach.getId());
          response.put("trainId", coach.getTrainId());
          response.put("coachType", coach.getType());
          response.put("success", "true");
          response.put("message", "Coach and Seats created");
          return response;
        }
        response.put("Error", "Coach Type invalid");
        response.put("success", "false");
        return response;
      } catch (Exception e) {
        response.put("Error", "Either Train ID incorrect or data invalid");
        response.put("Success", "false");
        return response;
      }
    }

    @GetMapping
    public List<Coach> findAll(){
      return coachService.findAll();
    }

    @PostMapping("/delete")
    public HashMap<String, String> deleteById(@RequestBody HashMap<String, String> request){
      User user;
      HashMap<String, String> response = new HashMap<String, String>();
      try {
        user = userService.findById(request.get("userId"));
      } catch (Exception e) {
        response.put("success", "false");
        response.put("Error", "Either User does not exist or Data invalid");
        return response;
      }
        seatService.deleteAllByCoachId(request.get("coachId"));
        coachService.deleteById(request.get("coachId"));
        response.put("Success", "true");
        return response;
    }
}
