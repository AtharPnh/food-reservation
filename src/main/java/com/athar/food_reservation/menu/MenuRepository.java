package com.athar.food_reservation.menu;

import com.athar.food_reservation.menu.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    /**
     * Uses DATE() function in query to compare only the date part, ignoring time
     * @param date
     * @param pageable
     * @return
     */
    @Query("SELECT m FROM Menu m WHERE DATE(m.createdAt) = DATE(:date)")
    Page<Menu> findAllByDate(@Param("date") LocalDateTime date, Pageable pageable);
}
