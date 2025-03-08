package com.athar.food_reservation.menu;

import org.springframework.stereotype.Service;

@Service
public class MenuMapper {

    public Menu toMenu(MenuRequest menuRequest) {
        return Menu.builder()
                .id(menuRequest.id())
                .meals(menuRequest.meals())
                .build();
    }
}
