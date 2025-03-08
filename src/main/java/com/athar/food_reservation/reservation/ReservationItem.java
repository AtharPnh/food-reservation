package com.athar.food_reservation.reservation;

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
public class ReservationItem extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "food_id")
    private Food food;
    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
    private int quantity;
    private double price;
}
