package com.irctc.backend.controller;

import com.irctc.backend.data.Train;
import com.irctc.backend.data.User;
import com.irctc.backend.data.Seat;
import com.irctc.backend.data.Coach;
import com.irctc.backend.service.TrainService;
import com.irctc.backend.service.UserService;
import com.irctc.backend.service.SeatService;
import com.irctc.backend.service.CoachService;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/train")
public class TrainController {

    @Autowired
    private TrainService trainService;
    @Autowired
    private UserService userService;
    @Autowired
    private CoachService coachService;
    @Autowired
    private SeatService seatService;

    @PostMapping("/create")
    public HashMap<String, String> create(@RequestBody HashMap<String, String> request){
      User user = userService.findById(request.get("userId"));
      HashMap<String, String> response = new HashMap<String, String>();
      if (!user.getRole().equals("Admin")) {
        response.put("success", "false");
        response.put("Error", "Access Denied");
        return response;
      }
      Train train = trainService.save(new Train(request.get("name")));
      String trainName = train.getName();
      response.put("name", trainName);
      response.put("success", "true");
      return response;
    }


    @GetMapping("/view")
    public HashMap<String, String> view(@RequestBody HashMap<String, String> request) {
      User user;
      HashMap<String, String> response = new HashMap<String, String>();
      try {
        user = userService.findById(request.get("userId"));
      } catch (Exception e) {
        response.put("success", "false");
        response.put("Error", "Either User does not exist or Data invalid");
        return response;
      }

      boolean isAdmin = user.getRole().equals("Admin");

      if (request.get("trainId") != null) {
        // Display All Seats in train
        try {
          Train train = trainService.findById(request.get("trainId"));
          List<Coach> coaches = coachService.findAllByTrainId(request.get("trainId"));
          for (Coach coach : coaches) {
            List <Seat> seats = seatService.findAllByCoachId(coach.getId());
            for (Seat seat : seats) {
              // Showing only available seats to users
              if (!isAdmin && seat.getStatus().equals("RESERVED"))
                continue;
              response.put(seat.getId(), seat.getStatus());
            }
          }
          response.put("success", "true");
          return response;
        } catch (Exception e) {
          response.put("success", "false");
          response.put("Error", "Train Id incorrect or data invalid");
          return response;
        }
      }
      if (request.get("coachId") != null) {
        // Display All seats in coach
        try {
          List<Seat> seats = seatService.findAllByCoachId(request.get("coachId"));
          for (Seat seat : seats) {
            if (!isAdmin && seat.getStatus().equals("RESERVED"))
                continue;
            response.put(seat.getId(), seat.getStatus());
          }
          response.put("success", "true");
          return response;
        } catch (Exception e) {
          response.put("success", "false");
          response.put("Error", "Coach Id incorrect or data invalid");
          return response;
        }
      }
      response.put("success", "false");
      response.put("Error", "Coach Id and Train Id not present in request");
      return response;
    }

    @GetMapping
    public List<Train> findAll(){
        return trainService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id){
        trainService.deleteById(id);
    }
}
