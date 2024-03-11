package com.restaurants.controller.contract.impl;

import com.restaurants.dto.RestaurantDTO;
import com.restaurants.entity.Restaurant;
import com.restaurants.mapper.RestaurantMapper;
import com.restaurants.request.RestaurantUpdateRequest;
import com.restaurants.controller.contract.RestaurantControllerContract;
import com.restaurants.exception.EErrorType;
import com.restaurants.exception.RestaurantManagerException;
import com.restaurants.request.RestaurantSaveRequest;
import com.restaurants.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantControllerContractImpl implements RestaurantControllerContract {
    private final RestaurantService restaurantService;
    @Override
    public RestaurantDTO save(RestaurantSaveRequest request) {
        Restaurant restaurant = RestaurantMapper.INSTANCE.convertToRestaurant(request);
        restaurant = restaurantService.save(restaurant);
        return RestaurantMapper.INSTANCE.convertToRestaurantDTO(restaurant);
    }

    @Override
    public RestaurantDTO delete(String id) {
        Optional<Restaurant> restaurant = restaurantService.findById(id);
        if(restaurant.isEmpty()){
            throw new RestaurantManagerException(EErrorType.RESTAURANT_NOT_FOUND);
        }
        restaurantService.delete(restaurant.get());
        return RestaurantMapper.INSTANCE.convertToRestaurantDTO(restaurant.get());
    }

    @Override
    public RestaurantDTO updateRestaurant(RestaurantUpdateRequest request) {
        Optional<Restaurant> restaurant = restaurantService.findById(request.id());
        if(restaurant.isEmpty()){
            throw new RestaurantManagerException(EErrorType.RESTAURANT_NOT_FOUND);
        }
        RestaurantMapper.INSTANCE.updateRestaurantFields(request,restaurant.get());
        restaurantService.save(restaurant.get());
        return RestaurantMapper.INSTANCE.convertToRestaurantDTO(restaurant.get());
    }

    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        Iterable<Restaurant> restaurantsList = restaurantService.findAll();
        return RestaurantMapper.INSTANCE.convertToRestaurantDTOs(restaurantsList);
    }
}
