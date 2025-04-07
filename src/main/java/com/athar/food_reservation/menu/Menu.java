package com.athar.food_reservation.menu;

import com.athar.food_reservation.common.BaseEntity;
import com.athar.food_reservation.meal.Meal;
import com.athar.food_reservation.food.Food;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Menu extends BaseEntity {
    private String title;
    private boolean isActive;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "meal_menu",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id "))
    private Set<Meal> meals = new HashSet<>();

}
