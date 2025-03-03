package com.athar.food_reservation.entities;

import com.athar.food_reservation.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Food extends BaseEntity {
    private String name;
    private FoodCategory category;
    @ManyToMany(mappedBy = "foods")
    private Set<Meal> meals = new HashSet<>();
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nutritional_info_id", referencedColumnName = "id")
    private NutritionalInfo nutritionalInfo;
    private double price;
    @ManyToMany(mappedBy = "foods")
    private Set<Menu> menus = new HashSet<>();

}
