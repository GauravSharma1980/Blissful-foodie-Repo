package com.blissful.foodie.service;

import com.blissful.foodie.dto.RestaurantDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestaurantService {

    // add logic

    RestaurantDTO add(RestaurantDTO restaurantDTO);

    // get single restaurant

    RestaurantDTO get(String id);

    // get all restaurant
    Page<RestaurantDTO> getRestaurants(Pageable pageable);

    // update
    RestaurantDTO updateRestaurant(String id,RestaurantDTO restaurantDTO);

    // delete

    void deleteRestaurant(String id);

    //search
    List<RestaurantDTO> searchByName(String keyword);

    Page<RestaurantDTO> getOpenRestaurants(Pageable pageable);

    List<RestaurantDTO> getAllRestaurants();

    Page<RestaurantDTO> getAllRestaurants(Pageable pageable);

}
