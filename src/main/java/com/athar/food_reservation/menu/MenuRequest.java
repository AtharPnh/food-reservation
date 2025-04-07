package com.athar.food_reservation.menu;

import com.athar.food_reservation.food.Food;
import com.athar.food_reservation.meal.Meal;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Set;

@Builder
public record MenuRequest(
        @NotNull(message = "Enter at least one meal, meals can not be null")
        Set<Meal> meals,
        String title,
        boolean isActive

) {
}
