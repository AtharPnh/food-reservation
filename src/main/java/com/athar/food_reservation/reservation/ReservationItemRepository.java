package com.athar.food_reservation.reservation;

import com.athar.food_reservation.reservation.ReservationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationItemRepository extends JpaRepository<ReservationItem, Long> {
}
