package com.athar.food_reservation.repositories;

import com.athar.food_reservation.entities.ReservationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationItemRepository extends JpaRepository<ReservationItem, Long> {
}
