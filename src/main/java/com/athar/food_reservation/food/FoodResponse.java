package com.athar.food_reservation.food;

import com.athar.food_reservation.meal.Meal;
import com.athar.food_reservation.meal.MealResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodResponse {
    private Long id;
    private String name;
    private FoodCategory category;
    private List<MealResponse> meals;
    private NutritionalInfo nutritionalInfo;
    private double price;
}
