package com.ulas.client;


import com.ulas.dto.RestaurantResponse;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "restaurantservice", url = "http://localhost:8086/api/v1/restaurants")
public interface RestaurantServiceClient {
    @GetMapping
    RestaurantResponse getAllCategories();
}
