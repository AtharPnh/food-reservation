package com.athar.food_reservation.menu;

import com.athar.food_reservation.food.Food;
import com.athar.food_reservation.meal.Meal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Uses actual MySQL
class MenuRepositoryTest {

    @Autowired
    MenuRepository menuRepository;

    @Test
    void save() {
        //Arrange
        var nimroo = Food.builder()
                .name("Nimroo")
                .price(20000)
                .createdAt(LocalDateTime.now())
                .createdBy(1L)
                .build();

        var meal1 = Meal.builder()
                .name("breakfast")
                .createdAt(LocalDateTime.now())
                .createdBy(1L)
                .foods(Set.of(nimroo))
                .build();

        var menu = Menu.builder()
                .meals(Set.of(meal1))
                .createdAt(LocalDateTime.now())
                .createdBy(1L)
                .build();

        //Act
        Menu saveMenue = menuRepository.save(menu);

        //Assert
        Assertions.assertNotNull(saveMenue);
        Assertions.assertTrue(saveMenue.getId() > 0);
    }

}