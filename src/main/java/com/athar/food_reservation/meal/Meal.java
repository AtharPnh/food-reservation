package com.athar.food_reservation.meal;

import com.athar.food_reservation.common.BaseEntity;
import com.athar.food_reservation.food.Food;
import com.athar.food_reservation.menu.Menu;
import com.athar.food_reservation.reservation.Reservation;
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
public class Meal extends BaseEntity {
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
    private String mealImage;
}
