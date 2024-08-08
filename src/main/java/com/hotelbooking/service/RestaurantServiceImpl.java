package com.hotelbooking.service;

import com.hotelbooking.dto.RestaurantDto;
import com.hotelbooking.model.Restaurant.Address;
import com.hotelbooking.model.Restaurant.Restaurant;
import com.hotelbooking.model.User;
import com.hotelbooking.repository.AddressRepository;
import com.hotelbooking.repository.RestaurantRepository;
import com.hotelbooking.repository.UserRepository;
import com.hotelbooking.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {


        Address address = addressRepository.save(req.getAddress());

        Restaurant restaurant= new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);

        if (restaurant.getCuisineType() != null){
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }

        if (restaurant.getDescription() != null){
            restaurant.setDescription(updatedRestaurant.getDescription());
        }
        if (restaurant.getName() != null){
            restaurant.setName(updatedRestaurant.getName());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> opt = restaurantRepository.findById(id);

        if(opt.isEmpty()){
            throw new Exception("Restaurant not found with id." + id);
        }

        return opt.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {

        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if(restaurant == null){
            throw new Exception("Restaurant not found with owner Id " + userId);
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);

        RestaurantDto dto = new RestaurantDto();

        dto.setId(restaurantId);
        dto.setTitle(restaurant.getName());
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());

        boolean isFavorite = false;

        List<RestaurantDto> favorites = user.getFavorites();
        for(RestaurantDto favourite: favorites){
            if(favourite.getId().equals(restaurantId)){
                isFavorite = true;
                break;
            }
        }

        if(isFavorite){
            favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
        }
        else favorites.add(dto);

        userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {

        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());

        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<RestaurantDto> getFavoriteRestaurants(Long id) {
        // Assuming that the user object is already populated with favorites
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        return user.getFavorites();
    }
}
