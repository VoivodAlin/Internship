package com.example.HotelReservation.controller;

import com.example.HotelReservation.service.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/distance")
public class DistanceController {

    private final DistanceService distanceService;

    @Autowired
    public DistanceController(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    @GetMapping("/user/{userId}/hotel/{hotelId}")
    public double getDistance(@PathVariable String userId, @PathVariable String hotelId) {
        try {
            return distanceService.calculateDistanceBetweenUserAndHotel(userId, hotelId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error calculating distance: " + e.getMessage(), e);
        }
    }

    @GetMapping("/user/{userId}/hotels")
    public Map<String, Double> getDistancesForUser(@PathVariable String userId) {
        try {
            return distanceService.calculateDistancesForUserAndSort(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error calculating distances: " + e.getMessage(), e);
        }
    }
}
