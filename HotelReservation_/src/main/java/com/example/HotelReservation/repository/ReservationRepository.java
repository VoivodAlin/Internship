package com.example.HotelReservation.repository;

import com.example.HotelReservation.domain.Hotel;
import com.example.HotelReservation.domain.Room;

import java.util.Date;

public interface ReservationRepository {
    Date getDate();
    void setDate(Date date);

    Hotel getHotel();
    void setHotel(Hotel hotel);

    Room getRoom();
    void setRoom(Room room);

    String getHotelName();
    String getRoomNumber();
    String getType();
    double getPrice();
}
