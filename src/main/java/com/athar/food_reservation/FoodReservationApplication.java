package com.athar.food_reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class FoodReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodReservationApplication.class, args);
	}

}
