package com.example.HotelReservation.repository;

import com.example.HotelReservation.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final List<User> users = new ArrayList<>();

    @Override
    public void uploadUsers(List<User> users) {
        this.users.addAll(users);
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public void deleteUser(String userId) {
        users.removeIf(user -> user.getName().equals(userId));
    }
}
