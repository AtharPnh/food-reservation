package com.athar.food_reservation.reservation;

import com.athar.food_reservation.common.BaseEntity;
import com.athar.food_reservation.meal.Meal;
import com.athar.food_reservation.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation extends BaseEntity {
    private LocalDate reservationForDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private ReservationStatus reservationStatus;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "reservation_meal",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id"))
    private Set<Meal> meals = new HashSet<>();
}
