package com.hotelbooking.controller;


import com.hotelbooking.dto.RestaurantDto;
import com.hotelbooking.model.Restaurant.Restaurant;
import com.hotelbooking.model.User;
import com.hotelbooking.service.RestaurantService;
import com.hotelbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String keyword
    ) throws Exception{

        User user = userService.findUserByJwtToken(jwt);

        List<Restaurant> restaurant = restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurants(
            @RequestHeader("Authorization") String jwt
    ) throws Exception{

        User user = userService.findUserByJwtToken(jwt);

        List<Restaurant> restaurant = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception{

        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> addToFavorite(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception{

        User user = userService.findUserByJwtToken(jwt);

        RestaurantDto restaurantDto = restaurantService.addToFavorites(id, user);
        return new ResponseEntity<>(restaurantDto, HttpStatus.OK);
    }
}
