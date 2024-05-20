package com.example.HotelReservation.controller;

import com.example.HotelReservation.domain.User;
import com.example.HotelReservation.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Value("${user.file.path:src/main/resources/users.json}")
    private String userFilePath;

    @Autowired
    public UserController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<User> getAllUsers() {
        try {
            File file = new File(userFilePath);
            User[] usersArray = objectMapper.readValue(file, User[].class);
            return Arrays.asList(usersArray);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading users from file", e);
        }
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable String userId) {
        try {
            File file = new File(userFilePath);
            User[] usersArray = objectMapper.readValue(file, User[].class);
            for (User user : usersArray) {
                if (user.getUserId().equals(userId)) {
                    return user;
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading users from file", e);
        }
    }

    @PostMapping("/create")
    public void createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating user", e);
        }
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable String userId, @RequestBody User updatedUser) {
        try {
            userService.updateUser(userId, updatedUser);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating user", e);
        }
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        try {
            userService.deleteUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting user", e);
        }
    }
}
