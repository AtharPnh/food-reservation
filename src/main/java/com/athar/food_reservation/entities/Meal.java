package com.athar.food_reservation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime createdAt;
    private String name;
    private MealCategory mealCategory;
    @ManyToMany(mappedBy = "meals")
    private Set<Reservation> reservations = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "food_meal",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private Set<Food> foods = new HashSet<>();
    @ManyToMany(mappedBy = "meals")
    private Set<Menu> menus = new HashSet<>();
}
