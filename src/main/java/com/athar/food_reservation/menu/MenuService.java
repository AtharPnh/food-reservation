package com.athar.food_reservation.menu;

import com.athar.food_reservation.common.PageResponse;
import com.athar.food_reservation.execptions.InvalidDateException;
import com.athar.food_reservation.execptions.ResourceNotFoundException;
import com.athar.food_reservation.meal.Meal;
import com.athar.food_reservation.meal.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public void delete(Long menuId) {
        var existingMenu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("The menu not found with id: " + menuId));

        menuRepository.delete(existingMenu);
    }

    public PageResponse<MenuResponse> findAllMenusForEachDay(LocalDateTime date, int page, int size) {
        validateDate(date);
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Menu> menus = menuRepository.findAllByDate(date, pageable);

        List<MenuResponse> menuResponseList = menus
                .getContent()
                .stream()
                .map(mapper::toMenuResponse)
                .collect(Collectors.toList());

        return PageResponse.<MenuResponse>builder()
                .content(menuResponseList)
                .number(menus.getNumber())
                .size(menus.getSize())
                .first(menus.isFirst())
                .last(menus.isLast())
                .totalElements(menus.getTotalElements())
                .totalPages(menus.getTotalPages())
                .build();
    }

    private void validateDate(LocalDateTime date) {
        if (date == null) {
            throw new InvalidDateException("Date cannot be null");
        }

        LocalDateTime now = LocalDateTime.now();
        if (date.isAfter(now)) {
            throw new InvalidDateException("Date cannot be in the future");
        }

        LocalDateTime minDate = now.minusYears(1);
        if (date.isBefore(minDate)) {
            throw new InvalidDateException("Date cannot be older than one year");
        }
    }
}
