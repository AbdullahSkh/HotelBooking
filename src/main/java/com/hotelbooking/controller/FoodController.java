package com.hotelbooking.controller;

import com.hotelbooking.model.Restaurant.Food;
import com.hotelbooking.model.Restaurant.Restaurant;
import com.hotelbooking.model.User;
import com.hotelbooking.service.FoodService;
import com.hotelbooking.service.RestaurantService;
import com.hotelbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                           @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Food> foods = foodService.searchFood(name);

        return new ResponseEntity<>(foods, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{restaurantId}")

    public ResponseEntity<List<Food>> getRestaurantFood(@PathVariable Long restaurantId,
                                                 @RequestParam boolean vegetarian,
                                                 @RequestParam boolean seasonal,
                                                 @RequestParam(required = false) String food_category,
                                                 @RequestParam boolean nonVeg,
                                                 @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Food> foods = foodService.getRestaurantFood(restaurantId, vegetarian, nonVeg, seasonal, food_category);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

}
