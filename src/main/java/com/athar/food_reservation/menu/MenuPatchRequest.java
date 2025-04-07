package com.athar.food_reservation.menu;

import com.athar.food_reservation.meal.Meal;

import java.util.Optional;
import java.util.Set;

public record MenuPatchRequest(
        Optional<String> title,
        Optional<Boolean> isActive,
        Optional<Set<Meal>> meals
) {
}
