package com.example.HotelReservation.service;

import com.example.HotelReservation.domain.Hotel;
import com.example.HotelReservation.domain.User;
import com.example.HotelReservation.util.DistanceCalculator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class DistanceService {

    @Value("src/main/resources/users.json")
    private String userFilePath;

    @Value("src/main/resources/hotels.json")
    private String hotelFilePath;

    private final ObjectMapper objectMapper;

    public DistanceService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<User> getAllUsers() throws IOException {
        File file = new File(userFilePath);
        User[] usersArray = objectMapper.readValue(file, User[].class);
        return Arrays.asList(usersArray);
    }

    public List<Hotel> getAllHotels() throws IOException {
        File file = new File(hotelFilePath);
        Hotel[] hotelsArray = objectMapper.readValue(file, Hotel[].class);
        return Arrays.asList(hotelsArray);
    }

    public double calculateDistanceBetweenUserAndHotel(String userId, String hotelId) throws IOException {
        User user = getAllUsers().stream()
                .filter(u -> userId.equals(u.getUserId()))
                .findFirst()
                .orElse(null);
        Hotel hotel = getAllHotels().stream()
                .filter(h -> hotelId.equals(h.getId()))
                .findFirst()
                .orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
        if (hotel == null) {
            throw new IllegalArgumentException("Hotel not found with ID: " + hotelId);
        }

        return DistanceCalculator.calculateDistance(user.getLatitude(), user.getLongitude(),
                hotel.getLatitude(), hotel.getLongitude());
    }

    public Map<String, Double> calculateDistancesForUserAndSort(String userId) throws IOException {
        User user = getAllUsers().stream()
                .filter(u -> userId.equals(u.getUserId()))
                .findFirst()
                .orElse(null);
        List<Hotel> hotels = getAllHotels();

        if (user == null) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }

        Map<String, Double> distances = new HashMap<>();
        for (Hotel hotel : hotels) {
            double distance = DistanceCalculator.calculateDistance(user.getLatitude(), user.getLongitude(),
                    hotel.getLatitude(), hotel.getLongitude());
            distances.put(hotel.getId(), distance);
        }

        // Sort the distances
        Map<String, Double> sortedDistances = new LinkedHashMap<>();
        distances.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(entry -> sortedDistances.put(entry.getKey(), entry.getValue()));

        return sortedDistances;
    }
}
