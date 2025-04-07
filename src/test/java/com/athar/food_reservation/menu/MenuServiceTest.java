package com.athar.food_reservation.menu;

import com.athar.food_reservation.food.Food;
import com.athar.food_reservation.meal.Meal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    // mock dependencies
    @Mock
    private MenuRepository menuRepository;
    @Mock
    private Mapper mapper;

    //inject mocks into the SUT
    @InjectMocks
    private MenuService menuService;

    @Test
    void test_save_Menu() {
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

        var menu = Menu.builder()
                .id(1L)
                .meals(Set.of(meal1))
                .build();

        // Mock behavior
        when(mapper.toMenu(menuRequest)).thenReturn(menu);
        when(menuRepository.save(menu)).thenReturn(menu);

        //Act
        Long result = menuService.save(menuRequest);

        //Assert
        assertNotNull(result);
        assertEquals(result, menu.getId());
        assertEquals(menu.getMeals(), menuRequest.meals());
        verify(mapper, times(1)).toMenu(menuRequest);
        verify(menuRepository, times(1)).save(menu);

    }

}