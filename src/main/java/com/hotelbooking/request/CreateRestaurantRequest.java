package com.hotelbooking.request;

import com.hotelbooking.model.Restaurant.Address;
import com.hotelbooking.model.Restaurant.ContactInformation;
import lombok.Data;

import java.util.List;


@Data
public class CreateRestaurantRequest {

    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;

}
