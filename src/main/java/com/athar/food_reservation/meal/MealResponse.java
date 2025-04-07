package com.athar.food_reservation.meal;

import com.athar.food_reservation.food.FoodResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MealResponse {
    private Long id;
    private String name;
    private String mealCategory;
    private List<FoodResponse> foods;
}
