package com.athar.food_reservation.reservation;

public enum ReservationStatus {
    PENDING(1), CONFIRMED(2), COMPLETED(3);

    private int id;

    ReservationStatus(int id) {
        this.id = id;
    }
}
