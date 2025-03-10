package com.athar.food_reservation.food;

import com.athar.food_reservation.common.BaseEntity;
import com.athar.food_reservation.food.Food;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class NutritionalInfo extends BaseEntity {
    private double calories;
    private double servingSize;
    private double fats;
    private double carbohydrates;
    private double protein;
    @OneToOne(mappedBy = "nutritionalInfo")
    private Food food;

}
