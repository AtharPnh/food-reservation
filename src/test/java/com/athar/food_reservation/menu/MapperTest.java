package com.athar.food_reservation.menu;

import com.athar.food_reservation.food.Food;
import com.athar.food_reservation.meal.Meal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

class MapperTest {
    private final Mapper mapper = new Mapper(); // Direct instance, no Spring needed

    @Test
    void test_mapping_menuRequest_to_the_map() {
        //Arrange
        var nimroo = Food.builder()
                .name("Nimroo")
                .createdAt(LocalDateTime.now())
                .createdBy(1L)
                .price(20000)
                .build();

        var meal1 = Meal.builder()
                .name("breakfast")
                .createdAt(LocalDateTime.now())
                .createdBy(1L)
                .foods(Set.of(nimroo))
                .build();

        var menuRequest = MenuRequest.builder()
                .meals(Set.of(meal1))
                .build();

        //Act
        var menu = mapper.toMenu(menuRequest);

        //Assertion
        Assertions.assertNotNull(menu.getMeals());
    }
}