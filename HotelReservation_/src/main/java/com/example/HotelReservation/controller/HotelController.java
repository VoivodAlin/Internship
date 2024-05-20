package com.example.HotelReservation.controller;

import com.example.HotelReservation.domain.Hotel;
import com.example.HotelReservation.domain.Room;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Value("src/main/resources/hotels.json")
    private String hotelFilePath;

    private final ObjectMapper objectMapper;

    public HotelController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<Hotel> getAllHotels() throws IOException {
        File file = new File(hotelFilePath);
        Hotel[] hotelsArray = objectMapper.readValue(file, Hotel[].class);
        List<Hotel> hotels = Arrays.asList(hotelsArray);
        return new ArrayList<>(hotels);
    }

    @GetMapping("/{hotelId}")
    public Hotel getHotelById(@PathVariable String hotelId) throws IOException {
        File file = new File(hotelFilePath);
        Hotel[] hotelsArray = objectMapper.readValue(file, Hotel[].class);
        List<Hotel> hotels = Arrays.asList(hotelsArray);

        for (Hotel hotel : hotels) {
            if (hotel.getId().equals(hotelId)) {
                return hotel;
            }
        }
        return null;
    }

    @PutMapping("/{hotelId}")
    public Hotel updateHotel(@PathVariable String hotelId, @RequestBody Hotel updatedHotel) throws IOException {
        File file = new File(hotelFilePath);
        Hotel[] hotelsArray = objectMapper.readValue(file, Hotel[].class);
        List<Hotel> hotels = new ArrayList<>(Arrays.asList(hotelsArray));

        for (Hotel hotel : hotels) {
            if (hotel.getId().equals(hotelId)) {
                hotel.setName(updatedHotel.getName());
                hotel.setLatitude(updatedHotel.getLatitude());
                hotel.setLongitude(updatedHotel.getLongitude());
                hotel.setRooms(updatedHotel.getRooms());

                objectMapper.writeValue(file, hotels);

                return hotel;
            }
        }
        return null;
    }
}
