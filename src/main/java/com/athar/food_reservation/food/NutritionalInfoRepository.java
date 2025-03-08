package com.athar.food_reservation.food;

import com.athar.food_reservation.food.NutritionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionalInfoRepository extends JpaRepository<NutritionalInfo, Long> {
}
