package com.athar.food_reservation.menu;

import com.athar.food_reservation.execptions.ResourceNotFoundException;
import com.athar.food_reservation.meal.Meal;
import com.athar.food_reservation.meal.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final Mapper mapper;
    private final MealRepository mealRepository;

    public Long save(MenuRequest menuRequest) {
        Menu menu = mapper.toMenu(menuRequest);
        return menuRepository.save(menu).getId();
    }


    public MenuResponse update(Long menuId, MenuRequest menuRequest) {
        Menu existingMenu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu not found with id: " + menuId));

        Set<Meal> updatedMeals = new HashSet<>();
        for (Meal incomingMeal : existingMenu.getMeals()) {
            if (incomingMeal.getId() != null) {
                Meal existingMeal = mealRepository.findById(incomingMeal.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Meal not found"));
                existingMeal.setName(incomingMeal.getName());
                existingMeal.setMealCategory(incomingMeal.getMealCategory());
                existingMeal.setFoods(incomingMeal.getFoods());
                updatedMeals.add(existingMeal);
            } else {
                updatedMeals.add(incomingMeal);
            }
        }
        existingMenu.setMeals(updatedMeals);
        existingMenu.setTitle(menuRequest.title());
        existingMenu.setActive(menuRequest.isActive());

        Menu savedMenu = menuRepository.save(existingMenu);

        return mapper.toMenuResponse(savedMenu);
    }
}
