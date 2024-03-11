package com.restaurants.controller.contract;

import com.restaurants.dto.RestaurantDTO;
import com.restaurants.request.RestaurantUpdateRequest;
import com.restaurants.request.RestaurantSaveRequest;

import java.util.List;

public interface RestaurantControllerContract {
    RestaurantDTO save(RestaurantSaveRequest request);

    RestaurantDTO delete(String id);

    RestaurantDTO updateRestaurant(RestaurantUpdateRequest request);
    List<RestaurantDTO> getAllRestaurants();
}
