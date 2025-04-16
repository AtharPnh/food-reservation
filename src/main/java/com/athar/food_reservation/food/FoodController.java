package com.athar.food_reservation.food;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    //todo: add food
    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<Long> save(
            @RequestBody @Valid FoodRequest foodRequest
    ) {
        return ResponseEntity.ok(foodService.save(foodRequest));
    }

    //todo: update food
    @PutMapping("/update/{food-id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<FoodResponse> updateFood(
            @PathVariable("food-id") Long foodId,
            @RequestBody @Valid FoodRequest foodRequest
    ) {
        return ResponseEntity.accepted()
                .body(foodService.updateFood(foodId, foodRequest));
    }



    //todo: delete food
    //todo: find all food

}
