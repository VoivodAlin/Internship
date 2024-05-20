package com.example.HotelReservation.controller;

import com.example.HotelReservation.domain.Hotel;
import com.example.HotelReservation.domain.Reservation;
import com.example.HotelReservation.domain.Room;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Value("src/main/resources/hotels.json")
    private String hotelFilePath;

    @Value("src/main/resources/reservations.json")
    private String reservationFilePath;

    private final ObjectMapper objectMapper;

    @Autowired
    public ReservationController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public Reservation makeReservation(@RequestBody Reservation reservation) throws IOException {

        File hotelFile = new File(hotelFilePath);
        Hotel[] hotelsArray = objectMapper.readValue(hotelFile, Hotel[].class);
        List<Hotel> hotels = Arrays.asList(hotelsArray);


        Hotel hotel = null;
        Room room = null;
        for (Hotel h : hotels) {
            if (h.getId().equals(reservation.getHotel().getId())) {
                hotel = h;
                for (Room r : h.getRooms()) {
                    if (r.getRoomNumber().equals(reservation.getRoom().getRoomNumber())) {
                        room = r;
                        break;
                    }
                }
                break;
            }
        }

        if (hotel == null || room == null || !room.isAvailable()) {
            throw new IllegalArgumentException("Invalid hotel or room information, or room is not available.");
        }


        room.setAvailable(false);


        objectMapper.writeValue(hotelFile, hotels);


        File reservationFile = new File(reservationFilePath);
        Reservation[] reservationsArray = new Reservation[0];
        if (reservationFile.exists()) {
            reservationsArray = objectMapper.readValue(reservationFile, Reservation[].class);
        }
        List<Reservation> reservations = new ArrayList<>(Arrays.asList(reservationsArray));


        reservations.add(reservation);


        objectMapper.writeValue(reservationFile, reservations);

        return reservation;
    }

}
