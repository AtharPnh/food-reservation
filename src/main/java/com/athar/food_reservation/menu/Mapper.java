package com.athar.food_reservation.menu;

import com.athar.food_reservation.food.Food;
import com.athar.food_reservation.food.FoodRequest;
import com.athar.food_reservation.food.FoodResponse;
import com.athar.food_reservation.meal.Meal;
import com.athar.food_reservation.meal.MealResponse;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class Mapper {

    public Menu toMenu(MenuRequest menuRequest) {
        return Menu.builder()
                .title(menuRequest.title())
                .isActive(menuRequest.isActive())
                .meals(menuRequest.meals())
                .build();
    }

    public MenuResponse toMenuResponse(Menu menu) {
        return MenuResponse.builder()
                .id(menu.getId())
                .title(menu.getTitle())
                .isActive(menu.isActive())
                .meals(menu.getMeals().stream()
                        .map(this::toMealResponse)
                        .collect(Collectors.toSet()))
                .build();
    }

    public MealResponse toMealResponse(Meal meal) {
        return MealResponse.builder()
                .id(meal.getId())
                .name(meal.getName())
                .mealCategory(meal.getMealCategory().toString())
                .foods(meal.getFoods().stream()
                        .map(this::toFoodResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    public FoodResponse toFoodResponse(Food food) {
        return FoodResponse.builder()
                .id(food.getId())
                .category(food.getCategory())
                .nutritionalInfo(food.getNutritionalInfo())
                .price(food.getPrice())
                .meals(food.getMeals().stream()
                        .map(this::toMealResponse)
                        .collect(Collectors.toList()))
                .name(food.getName())
                .build();
    }

    public Food toFood(FoodRequest foodRequest) {
        return Food.builder()
                .price(foodRequest.price())
                .name(foodRequest.name())
                .category(foodRequest.category())
                .meals(new HashSet<>(foodRequest.meals()))
                .nutritionalInfo(foodRequest.nutritionalInfo())
                .build();
    }
}
