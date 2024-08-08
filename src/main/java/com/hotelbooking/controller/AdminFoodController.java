package com.hotelbooking.controller;

import com.hotelbooking.model.Restaurant.Food;
import com.hotelbooking.model.Restaurant.Restaurant;
import com.hotelbooking.model.User;
import com.hotelbooking.request.CreateFoodRequest;
import com.hotelbooking.response.MessageResponse;
import com.hotelbooking.service.FoodService;
import com.hotelbooking.service.RestaurantService;
import com.hotelbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                                    @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
        Food food = foodService.createFood(req,req.getCategory(),restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        foodService.deleteFood(id);

        MessageResponse response = new MessageResponse();
        response.setMessage("Deleted Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable Long id,
                                           @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.updateAvailabilityStatus(id);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }
}
