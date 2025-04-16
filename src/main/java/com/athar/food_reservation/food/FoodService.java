package com.athar.food_reservation.food;

import com.athar.food_reservation.execptions.ResourceNotFoundException;
import com.athar.food_reservation.menu.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final Mapper mapper;

    public FoodResponse updateFood(Long foodId, FoodRequest foodRequest) {

        var exsitingFood = foodRepository.findById(foodId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no food with this id, " + foodId));

        exsitingFood.setName(foodRequest.name());
        exsitingFood.setCategory(foodRequest.category());
        exsitingFood.setPrice(foodRequest.price());
        if (foodRequest.nutritionalInfo() != null) {
            exsitingFood.setNutritionalInfo(foodRequest.nutritionalInfo());
        }

        foodRepository.save(exsitingFood);

        return mapper.toFoodResponse(exsitingFood);
    }

    public Long save(FoodRequest foodRequest) {
        var food = mapper.toFood(foodRequest);
        return foodRepository.save(food).getId();
    }
}
