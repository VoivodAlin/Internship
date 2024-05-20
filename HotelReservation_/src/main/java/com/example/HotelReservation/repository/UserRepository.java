package com.example.HotelReservation.repository;

import com.example.HotelReservation.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    void uploadUsers(List<User> users);

    List<User> getAllUsers();

    void deleteUser(String userId);
}
