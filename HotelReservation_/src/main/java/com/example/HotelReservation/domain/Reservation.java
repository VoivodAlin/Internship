package com.example.HotelReservation.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Reservation {

    private Date date;
    private Hotel hotel;
    private Room room;

    public Reservation() {
    }

    public Reservation(Date date, Hotel hotel, Room room) {
        this.date = date;
        this.hotel = hotel;
        this.room = room;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "date=" + date +
                ", hotel=" + hotel.getName() +
                ", room=" + room.getRoomNumber() +
                '}';
    }
}
