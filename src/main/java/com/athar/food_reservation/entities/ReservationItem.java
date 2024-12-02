package com.athar.food_reservation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ReservationItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "food_id")
    private Food food;
    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
    private LocalDateTime reservationTime;
    private int quantity;
    private double price;
}
