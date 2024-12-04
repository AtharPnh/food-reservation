package com.athar.food_reservation.services;

import com.athar.food_reservation.entities.User;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    public boolean isValidUser(User user) {
        // Example validation (replace with database/service check)
        return "testuser".equals(user.getUsername()) && "password123".equals(user.getPassword());
    }
}
