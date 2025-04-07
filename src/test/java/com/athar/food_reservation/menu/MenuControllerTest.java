package com.athar.food_reservation.menu;

import com.athar.food_reservation.jwt.JwtUtil;
import com.athar.food_reservation.meal.Meal;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers = MenuController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class MenuControllerTest {
    @MockitoBean
    @Autowired
    private MenuService menuService;
    @MockitoBean
    @Autowired
    private JwtUtil jwtUtil;
    @InjectMocks
    private MenuController menuController;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(menuController).build();
    }

    @Test
    @WithMockUser(authorities = "MENU_WRITE")
    void test_saveMenu() throws Exception {

        //Arrange
        var menuRequest = MenuRequest.builder()
                .id(1L)
                .meals(Set.of(new Meal()))
                .build();
        when(menuService.save(menuRequest)).thenReturn(1L);

        //Act
//        mockMvc.perform(post("/menu/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(menuRequest)))
//                .andExpect(status().isOk())
//                .andExpect(content().string("1"));
    }
}