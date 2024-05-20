package com.example.HotelReservation.repository;

public interface RoomRepository {
    String getRoomNumber();
    void setRoomNumber(String roomNumber);

    String getType();
    void setType(String type);

    double getPrice();
    void setPrice(double price);

    boolean isAvailable();
    void setAvailable(boolean available);
}
