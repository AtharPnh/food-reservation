package com.athar.food_reservation.food;

public enum FoodCategory {
    APPETIZER(1), MAIN_COURSE(2), DESSERT(3), SIDE_DISH(4), BEVERAGE(5);

    private int id;

    FoodCategory(int id) {
        this.id = id;
    }
}
