package com.athar.food_reservation.repositories;

import com.athar.food_reservation.entities.NutritionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionalInfoRepository extends JpaRepository<NutritionalInfo, Long> {
}
