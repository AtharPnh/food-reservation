package com.athar.food_reservation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class NutritionalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double calories;
    private double servingSize;
    private double fats;
    private double carbohydrates;
    private double protein;
    @OneToOne(mappedBy = "nutritionalInfo")
    private Food food;

}
