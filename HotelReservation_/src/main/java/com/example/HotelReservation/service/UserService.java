package com.example.HotelReservation.service;

import com.example.HotelReservation.domain.User;
import com.example.HotelReservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.uploadUsers(List.of(user));
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void deleteUser(String userId) {
        userRepository.deleteUser(userId);
    }

    public void updateUser(String userId, User updatedUser) {
    }
}
