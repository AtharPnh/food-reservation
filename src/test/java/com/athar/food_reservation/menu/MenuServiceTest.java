package com.athar.food_reservation.menu;

import com.athar.food_reservation.common.PageResponse;
import com.athar.food_reservation.execptions.InvalidDateException;
import com.athar.food_reservation.food.Food;
import com.athar.food_reservation.food.FoodResponse;
import com.athar.food_reservation.meal.Meal;
import com.athar.food_reservation.meal.MealRepository;
import com.athar.food_reservation.meal.MealResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    // mock dependencies
    @Mock
    private MenuRepository menuRepository;
    @Mock
    private MealRepository mealRepository;
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

    @Test
    void test_findAllMenusForEachDay_WithValidDate() {
        //Arrange
        LocalDateTime date = LocalDateTime.now().minusDays(1);
        int page = 0;
        int size = 10;

        // Create a mock Page object
        Page<Menu> mockPage = mock(Page.class);
        when(mockPage.getContent()).thenReturn(List.of(new Menu()));
        when(mockPage.getNumber()).thenReturn(page);
        when(mockPage.getSize()).thenReturn(size);
        when(mockPage.getTotalElements()).thenReturn(1L);
        when(mockPage.getTotalPages()).thenReturn(1);
        when(mockPage.isFirst()).thenReturn(true);
        when(mockPage.isLast()).thenReturn(true);

        when(menuRepository.findAllByDate(eq(date), any(Pageable.class)))
                .thenReturn(mockPage);

        // Act
        PageResponse<MenuResponse> result = menuService.findAllMenusForEachDay(date, page, size);

        // Assert
        assertNotNull(result);
        assertEquals(page, result.getNumber());
        assertEquals(size, result.getSize());
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertTrue(result.isFirst());
        assertTrue(result.isLast());


    }

    @Test
    void test_findAllMenusForEachDay_WithInValidDate() {
        //Arrange
        LocalDateTime date = LocalDateTime.now().plusDays(1);
        assertThrows(InvalidDateException.class, () ->
                menuService.findAllMenusForEachDay(date, 0, 10));
    }

    @Test
    void test_update_existingMenu() {
        //Arrange
        Long menuId = 1L;

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

        var existingMenu = Menu.builder()
                .id(menuId)
                .title("old title")
                .isActive(false)
                .meals(Set.of(meal1))
                .build();
        var menuRequest = MenuRequest.builder()
                .title("today")
                .isActive(true)
                .meals(Set.of(meal1))
                .build();

        when(menuRepository.findById(menuId))
                .thenReturn(Optional.of(existingMenu));

        when(menuRepository.save(any(Menu.class)))
                .thenReturn(existingMenu);

        var mealResponse = MealResponse
                .builder()
                .mealCategory("category")
                .foods(List.of(new FoodResponse()))
                .id(meal1.getId())
                .name(meal1.getName())
                .build();

        var expectedResponse = MenuResponse.builder()
                .id(menuId)
                .title("today")
                .isActive(true)
                .meals(Set.of(mealResponse))
                .build();
        when(mapper.toMenuResponse(any(Menu.class)))
                .thenReturn(expectedResponse);

        //Act
        MenuResponse menuResponse = menuService.update(menuId, menuRequest);

        //Assert
        //Assert
        assertNotNull(menuResponse);
        assertEquals(menuId, menuResponse.getId());
        assertEquals("today", menuResponse.getTitle());
        assertTrue(menuResponse.isActive());
        assertEquals(1, menuResponse.getMeals().size());

        // Verify the interactions
        verify(menuRepository).findById(menuId);
        verify(menuRepository).save(any(Menu.class));
        verify(mapper).toMenuResponse(any(Menu.class));
    }

}