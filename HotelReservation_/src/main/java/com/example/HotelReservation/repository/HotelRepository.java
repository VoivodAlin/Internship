package com.example.HotelReservation.repository;

import com.example.HotelReservation.domain.Room;

import java.util.List;

public interface HotelRepository {
    String getId();
    void setId(String id);

    String getName();
    void setName(String name);

    double getLatitude();
    void setLatitude(double latitude);

    double getLongitude();
    void setLongitude(double longitude);

    List<Room> getRooms();
    void setRooms(List<Room> rooms);
}
