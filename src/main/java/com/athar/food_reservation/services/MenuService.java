package com.athar.food_reservation.services;

import com.athar.food_reservation.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    
}
