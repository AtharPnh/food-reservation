package com.athar.food_reservation.menu;

import com.athar.food_reservation.food.Food;
import com.athar.food_reservation.meal.Meal;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record MenuRequest(
        @NotNull(message = "id con not be null")
        @NotEmpty(message = "id con not be empty")
        long id,
        @NotNull(message = "Enter at least one meal, meals can not be null")
        @NotEmpty(message = "Enter at least one meal, meals can not be empty")
        Set<Meal> meals
) {
}
