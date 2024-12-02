package com.athar.food_reservation.entities;

public enum MealCategory {
    BREAKFAST(1),LUNCH(2), DINNER(3), SNACK(4);

    private int id;

    MealCategory(int id) {
        this.id = id;
    }
}
