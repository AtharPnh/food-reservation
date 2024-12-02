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
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private FoodCategory category;
    @ManyToMany(mappedBy = "foods")
    private Set<Meal> meals = new HashSet<>();
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nutritional_info_id", referencedColumnName = "id")
    private NutritionalInfo nutritionalInfo;
    private double price;
    private LocalDateTime createdAt;
    @ManyToMany(mappedBy = "foods")
    private Set<Menu> menus = new HashSet<>();

}
