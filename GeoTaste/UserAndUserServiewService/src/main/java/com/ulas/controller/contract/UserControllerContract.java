package com.ulas.controller.contract;

import com.ulas.dto.RestaurantDTO;
import com.ulas.dto.UserDTO;
import com.ulas.request.UserSaveRequest;
import com.ulas.request.UserUpdateRequest;

import java.util.List;

public interface UserControllerContract {
    UserDTO saveUser(UserSaveRequest request);
    UserDTO delete(Long id);

    UserDTO updateUser(UserUpdateRequest request);
    List<RestaurantDTO> getRestaurantRecommendations(Long userId);
    double calculateDistance(double userLat, double userLon, double restaurantLat, double restaurantLon);

    int compareRestaurants(RestaurantDTO r1, RestaurantDTO r2, double userLat, double userLon);
}
