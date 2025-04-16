package com.athar.food_reservation.food;

import com.athar.food_reservation.meal.Meal;
import com.athar.food_reservation.meal.MealResponse;
import com.athar.food_reservation.menu.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(MockitoExtension.class)
class FoodControllerTest {

    @Mock
    private FoodService foodService;
    @Mock
    private Mapper mapper;
    @InjectMocks
    private FoodController foodController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(foodController).build();
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "STAFF"})
    void test_saveFood() throws Exception {
        //Arrange
        var foodRequest = FoodRequest.builder()
                .meals(List.of(new Meal()))
                .price(5.67)
                .name("Makaroni")
                .nutritionalInfo(new NutritionalInfo())
                .category(FoodCategory.BEVERAGE)
                .build();

        when(foodService.save(any())).thenReturn(1L);

        mockMvc.perform(post("/foods/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(foodRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

    }

    @Test
    @WithMockUser(roles = {"ADMIN", "STAFF"})
    void test_updateFood() throws Exception {
        //Arrange
        var foodRequest = FoodRequest.builder()
                .meals(List.of(new Meal()))
                .price(5.67)
                .name("Makaroni")
                .nutritionalInfo(new NutritionalInfo())
                .category(FoodCategory.BEVERAGE)
                .build();

        Long foodId = 1L;

        var foodResponse = FoodResponse.builder()
                .id(foodId)
                .price(foodRequest.price())
                .nutritionalInfo(foodRequest.nutritionalInfo())
                .category(foodRequest.category())
                .name(foodRequest.name())
                .meals(List.of(new MealResponse()))
                .build();

        when(foodService.updateFood(eq(foodId), any())).thenReturn(foodResponse);

        mockMvc.perform(put("/foods/update/{food-id}", foodId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(foodRequest)))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(foodId))
                .andExpect(jsonPath("$.name").value("Makaroni"))
                .andExpect(jsonPath("$.meals").isArray())
                .andExpect(jsonPath("$.meals.length()").value(1));

    }
}