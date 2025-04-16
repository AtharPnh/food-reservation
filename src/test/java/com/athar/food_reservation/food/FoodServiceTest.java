package com.athar.food_reservation.food;

import com.athar.food_reservation.execptions.ResourceNotFoundException;
import com.athar.food_reservation.meal.Meal;
import com.athar.food_reservation.menu.Mapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FoodServiceTest {
    @Mock
    private FoodRepository foodRepository;
    @Mock
    private Mapper mapper;
    @InjectMocks
    private FoodService foodService;

    @Test
    void test_save() {
        //Arrange
        var foodRequest = FoodRequest.builder()
                .meals(List.of(new Meal()))
                .price(5.67)
                .name("Makaroni")
                .nutritionalInfo(new NutritionalInfo())
                .category(FoodCategory.BEVERAGE)
                .build();

        var food = Food.builder()
                .id(1L)
                .build();

        when(mapper.toFood(foodRequest)).thenReturn(food);
        when(foodRepository.save(food)).thenReturn(food);

        //Act
        Long result = foodService.save(foodRequest);

        //Assert
        assertNotNull(result);
        assertEquals(result, food.getId());
        verify(mapper, times(1)).toFood(foodRequest);
        verify(foodRepository, times(1)).save(food);
    }

    @Test
    void test_UpdateFood_nonExistingFood() {
        //Arrange
        var foodRequest = FoodRequest.builder()
                .meals(List.of(new Meal()))
                .price(5.67)
                .name("Makaroni")
                .nutritionalInfo(new NutritionalInfo())
                .category(FoodCategory.BEVERAGE)
                .build();
        Long id = 1L;

        when(foodRepository.findById(id))
                .thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(ResourceNotFoundException.class, () ->
                foodService.updateFood(id, foodRequest));

        // Verify the interaction
        verify(foodRepository).findById(id);
        verify(foodRepository, never()).save(any());

    }

}