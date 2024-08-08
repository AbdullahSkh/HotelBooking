package com.hotelbooking.service;

import com.hotelbooking.dto.RestaurantDto;
import com.hotelbooking.model.Restaurant.Restaurant;
import com.hotelbooking.model.User;
import com.hotelbooking.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurants();

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long id) throws Exception;

    public Restaurant getRestaurantByUserId(Long UserId) throws Exception;

    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;

    public List<RestaurantDto> getFavoriteRestaurants(Long Id);

    public Restaurant updateRestaurantStatus(Long id) throws Exception;
}
