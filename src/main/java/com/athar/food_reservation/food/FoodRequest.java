package com.athar.food_reservation.food;

import com.athar.food_reservation.meal.Meal;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import java.util.List;

@Builder
public record FoodRequest(
        String name,
        FoodCategory category,
        List<Meal> meals,
        NutritionalInfo nutritionalInfo,
        @NotNull(message = "Price can not be null")
        double price

) {
}
