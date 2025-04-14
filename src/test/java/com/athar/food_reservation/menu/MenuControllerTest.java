package com.athar.food_reservation.menu;

import com.athar.food_reservation.jwt.JwtUtil;
import com.athar.food_reservation.meal.Meal;
import com.athar.food_reservation.meal.MealResponse;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class MenuControllerTest {

    @Mock
    private MenuService menuService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private MenuController menuController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(menuController).build();
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "STAFF"})
    void test_saveMenu() throws Exception {
        // Arrange
        var menuRequest = MenuRequest.builder()
                .title("Daily Menu")
                .meals(Set.of(new Meal()))
                .isActive(true)
                .build();

        when(menuService.save(any(MenuRequest.class))).thenReturn(1L);

        // Act & Assert
        mockMvc.perform(post("/menus/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN", "STAFF"})
    void test_updateMenu() throws Exception {
        //Arrange
        Long menuId = 1L;
        var menuRequest = MenuRequest.builder()
                .title("Daily Menu")
                .meals(Set.of(new Meal()))
                .isActive(true)
                .build();
        var expectedResponse = MenuResponse.builder()
                .id(menuId)
                .title(menuRequest.title())
                .isActive(true)
                .meals(Set.of(new MealResponse()))
                .build();

        when(menuService.update(eq(menuId), any(MenuRequest.class)))
                .thenReturn(expectedResponse);

        //Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/menus/update/{menu-id}", menuId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuRequest)))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(menuId))
                .andExpect(jsonPath("$.title").value("Daily Menu"))
                .andExpect(jsonPath("$.meals").isArray())
                .andExpect(jsonPath("$.meals.length()").value(1));

        verify(menuService).update(eq(menuId), any(MenuRequest.class));
    }

}