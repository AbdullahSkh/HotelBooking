package com.hotelbooking.service;

import com.hotelbooking.model.Restaurant.Category;
import com.hotelbooking.model.Restaurant.Food;
import com.hotelbooking.model.Restaurant.Restaurant;
import com.hotelbooking.repository.FoodRepository;
import com.hotelbooking.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantFood(
            Long restaurantId,
            boolean isVegetarian,
            boolean isNonVeg,
            boolean isSeasonal,
            String foodCategory
    );

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailabilityStatus(Long foodId) throws Exception;
}
