package com.athar.food_reservation.menu;

import com.athar.food_reservation.meal.Meal;
import com.athar.food_reservation.meal.MealResponse;
import lombok.*;

import java.security.Principal;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponse {
    private Long id;
    private Set<MealResponse> meals;
    private String title;
    private boolean isActive;
}
