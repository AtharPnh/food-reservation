package com.athar.food_reservation.menu;

import com.athar.food_reservation.meal.Meal;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponse {
    private Long id;
    private Set<Meal> meals;
}
