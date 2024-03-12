package com.restaurants.service;

import com.restaurants.entity.Restaurant;
import com.restaurants.general.BaseEntityService;
import com.restaurants.repository.RestaurantRepository;

import org.springframework.stereotype.Service;

@Service
public class RestaurantService extends BaseEntityService<Restaurant, RestaurantRepository> {

    protected RestaurantService(RestaurantRepository repository) {
        super(repository);
    }


}
